package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.PublicationBean;
import com.example.demo.auth.dto.Login;
import com.example.demo.auth.dto.Register;
import com.example.demo.auth.dto.response.JwtResponse;
import com.example.demo.auth.dto.response.ResponseMessage;
import com.example.demo.auth.security.jwt.JwtProvider;
import com.example.demo.dao.MembreRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Role;
import com.example.demo.enumeration.RoleName;
import com.example.demo.proxies.Evenementproxy;
import com.example.demo.proxies.OutilProxy;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;

@RestController
public class MembreRestController {
	@Autowired
	IMemberService memberservice;
	@Autowired
	PublicationProxy publicationproxy;
	@Autowired
	OutilProxy outilProxy;
	@Autowired
	Evenementproxy evenementproxy;
	@Autowired
	MembreRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	IMemberService memberService;
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/api/auth/register")
	public ResponseEntity<ResponseMessage> registeruser(@Validated @RequestBody Register registerRequest) {
		if (userRepository.existsByCin(registerRequest.getCin())) {
			return new ResponseEntity<>(new ResponseMessage("Cin is already Taken"), HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Email is already Taken"), HttpStatus.BAD_REQUEST);
		}
		// create User account ;
		Membre user = new EnseignantChercheur(registerRequest.getCin(), registerRequest.getNom(), registerRequest.getPrenom(),
				registerRequest.getDate(), registerRequest.getCv(),
				registerRequest.getEmail(),passwordEncoder.encode(registerRequest.getPassword()),"grade","etablisement");

		Set<String> rolesInRequest = registerRequest.getRole();
		Set<Role> roles = new HashSet<>();
		rolesInRequest.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
				roles.add(adminRole);
				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
				roles.add(userRole);

			}

		});
		user.setRole(roles);
		memberService.addMember(user);
		return new ResponseEntity<>(new ResponseMessage("User Registred Successfully"), HttpStatus.OK);
	}

	@PostMapping("/api/auth/login")
	public ResponseEntity<?> loginruser(@Validated @RequestBody Login loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getCin(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		

}

	@GetMapping(value = "/membres")
	public List<Membre> findAllmembres()
	{
		return memberservice.findAll();
	}
	
	@GetMapping(value = "/membres/etudiants")
	public List<Etudiant> findAllEtudiants(){
		return memberservice.findAllEtudiants();
	}

	@GetMapping(value = "/membres/enseignants")
	public List<EnseignantChercheur> findAllEnseignant(){
		return memberservice.findAllEnseignants();
	}

	@GetMapping(value = "/membres/{id}")
	public Membre findoneMembre(@PathVariable Long id)
	{
		return memberservice.findMember(id);
	}
	
	@PostMapping(value = "/membres/etudiant")
	public Membre addMembre(@RequestBody Etudiant etd)
	{
		return memberservice.addMember(etd);
	}

	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur ens)
	{
		return memberservice.addMember(ens);
	}
	@DeleteMapping(value="/membres/{id}")
	public void deletemembre(@PathVariable Long id) {
		memberservice.deleteMember(id);
	}
	@DeleteMapping(value="/membres/enseignant/{id}")
	public void deleteEns(@PathVariable Long id) {
		EnseignantChercheur ens=memberservice.findEnseignant(id);
		for(Etudiant etd : ens.getListetud()) {
			etd.setEncadrant(null);
			memberservice.updateMember(etd);
		}
		memberservice.deleteMember(id);
	}
	@PutMapping(value="/membres/etudiant/{id}")
	public Membre updatemembre(@PathVariable Long id, @RequestBody Etudiant p)
	{
		p.setId(id);
		return memberservice.updateMember(p);
	}

	@PutMapping(value="/membres/enseignant/{id}")
	public Membre updateMembre(@PathVariable Long id, @RequestBody EnseignantChercheur p)
	{
		p.setId(id);
	       return memberservice.updateMember(p);
	}
	@PutMapping(value="/membres/etudiant")
	public Membre affecter(@RequestParam Long idetd , @RequestParam Long idens )
	{
		
	       return memberservice.affecterencadrantToetudiant(idetd, idens);
	}
	@GetMapping("/publications")
	public CollectionModel<PublicationBean> listerpublication()
	{
		return publicationproxy.listeDesPublications();
		
	}
	@GetMapping("/outils")
	public CollectionModel<OutilBean> listeroutil(){
		return outilProxy.listeDesoutils(); 
	}
	@GetMapping("/evenements")
	public CollectionModel<EvenementBean> listerevenements(){
		return evenementproxy.listeDesEvenements();  
	}
	@GetMapping("/publications/{id}")
	public EntityModel<PublicationBean> listerunepublication(@PathVariable Long id)
	{
		return publicationproxy.recupererUnePublication(id);
		
	}
	@GetMapping("/outils/{id}")
	public EntityModel<OutilBean> listeruneoutil(@PathVariable Long id)
	{
		return outilProxy.recupererUneoutil(id); 
		
	}
	@GetMapping("/evenements/{id}")
	public EntityModel<EvenementBean> listeruneevenement(@PathVariable Long id)
	{
		return evenementproxy.recupererUneEvenement(id); 
		
	}
	@GetMapping("/publications/auteur/{id}")
	public List<PublicationBean>listerpublicationbymembre(@PathVariable(name="id") Long idaut)
	{
		return memberservice.findPublicationparauteur(idaut);		
	}
	@GetMapping("/outils/developpeur/{id}")
	public List<OutilBean>listeroutilbydeveloppeur(@PathVariable(name="id") Long iddev)
	{
		return memberservice.findoutilpardeveloppeur(iddev)	; 	
	}
	@GetMapping("/evenements/organisateur/{id}")
	public List<EvenementBean>listerevenementsbyorganisateur(@PathVariable(name="id") Long idorg)
	{
		return memberservice.findevenementparorganisateur(idorg); 	
	}
	
	@GetMapping("/fullmember/{id}")
	public Membre findAFullMember(@PathVariable(name="id") Long id)
	{
		Membre mbr=memberservice.findMember(id);
		mbr.setPubs(memberservice.findPublicationparauteur(id));
		mbr.setEvenements(memberservice.findevenementparorganisateur(id));
		mbr.setOutils(memberservice.findoutilpardeveloppeur(id));
		return mbr;		
	}


}
