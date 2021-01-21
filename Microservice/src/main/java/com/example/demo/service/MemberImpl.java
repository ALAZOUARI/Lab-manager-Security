package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.PublicationBean;
import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.dao.Membreevtrepository;
import com.example.demo.dao.Membreoutrepository;
import com.example.demo.dao.Membrepubrepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Membre_Evenement;
import com.example.demo.entities.Membre_Evt_Ids;
import com.example.demo.entities.Membre_Out_Ids;
import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Pub_Ids;
import com.example.demo.entities.Membre_Publication;
import com.example.demo.proxies.Evenementproxy;
import com.example.demo.proxies.OutilProxy;
import com.example.demo.proxies.PublicationProxy;

@Service
public class MemberImpl implements IMemberService {
	
	@Autowired
	MembreRepository membreRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheur;
	@Autowired
	Membrepubrepository membrepubrepository;
	@Autowired
	Membreoutrepository membreoutrepository;
	@Autowired
	Membreevtrepository membreevtrepository; 
	@Autowired(required = true)
	PublicationProxy proxy;
	@Autowired(required = true)
	OutilProxy oproxy;
	@Autowired(required = true)
	Evenementproxy eproxy ; 
	

	public Membre addMember(Membre m) {
		membreRepository.save(m);
		return m;
	}

	
	public void deleteMember(Long id) {
		
		membreRepository.deleteById(id);

	}
	public Membre updateMember(Membre m) {
		
		return membreRepository.saveAndFlush(m);
	}
	public Membre findMember(Long id) {
	Membre m= (Membre)membreRepository.findById(id).get();
		return m;
	}
	public EnseignantChercheur findEnseignant(Long id) {
		EnseignantChercheur e = (EnseignantChercheur) membreRepository.findById(id).get();
		return e; 
	}
	public List<Membre> findAll() {
		
		return membreRepository.findAll();
	}

	public Optional<Membre> findByCin(String cin) {
		return membreRepository.findByCin(cin);
	}
	public Membre findByEmail(String email) {
		return membreRepository.findByEmail(email);
	}
	public List<Membre> findByNom(String nom) {
		return membreRepository.findByNom(nom);
	}
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}
	public List<EnseignantChercheur> findByGrade(String grade) {
		
		return enseignantChercheur.findByGrade(grade);
	}
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
	
		return enseignantChercheur.findByEtablissement(etablissement);
	}
	public List<Etudiant> findAllEtudiants() {
		return etudiantRepository.findAll();
	}
	public List<EnseignantChercheur> findAllEnseignants() {
		return enseignantChercheur.findAll();
	}


	@Override
	public Etudiant affecterencadrantToetudiant(Long idetd, Long idens) {
		Etudiant etd= etudiantRepository.findById(idetd).get();
		EnseignantChercheur ens= enseignantChercheur.findById(idens).get();
		etd.setEncadrant(ens);

		return etudiantRepository.save(etd);
	}


	@Override
	public void affecterauteurTopublication(Long idauteur, Long idpub) {
		Membre mbr= membreRepository.findById(idauteur).get();
		Membre_Publication mbs= new Membre_Publication();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Pub_Ids(idpub, idauteur));
		membrepubrepository.save(mbs);
	}
	@Override
	public void affecterdeveloppeurTooutil(Long iddev, Long idout) {
		Membre mbr=membreRepository.findById(iddev).get(); 
		Membre_Outil mbo = new Membre_Outil() ;
		mbo.setDeveloppeur(mbr);
		mbo.setId(new Membre_Out_Ids(idout,iddev));
		membreoutrepository.save(mbo); 
	}


	@Override
	public void affecterorganisateurToevt(Long idorg, Long idevt) {
		Membre mbr=membreRepository.findById(idorg).get();
		Membre_Evenement mbe = new Membre_Evenement(); 
		mbe.setOrganisateur(mbr);
		mbe.setId(new Membre_Evt_Ids(idevt,idorg));
		membreevtrepository.save(mbe); 
		
	}



	@Override
	public List<PublicationBean> findPublicationparauteur(Long idauteur) {
		List<PublicationBean> pubs=new ArrayList<PublicationBean>();
	
		List< Membre_Publication> idpubs=membrepubrepository.findpubId(idauteur);
		
		idpubs.forEach(s->{
			System.out.println(s);
			pubs.add(proxy.recupererUnePublication(s.getId().getPublication_id()).getContent());
			
		}
		);
		
		return pubs;
	}
	@Override
	public List<OutilBean> findoutilpardeveloppeur(Long iddev) {
		List<OutilBean> outils= new ArrayList<OutilBean>(); 
		List<Membre_Outil> idoutils = membreoutrepository.findoutId(iddev);
		idoutils.forEach(s->{
			System.out.println(s);
			outils.add(oproxy.recupererUneoutil(s.getId().getOutil_id()).getContent());
		});
		return outils; 
	}


	@Override
	public List<EvenementBean> findevenementparorganisateur(Long idorg) {
		List<EvenementBean> events = new ArrayList<EvenementBean>();
		List<Membre_Evenement> idevents = membreevtrepository.findevtId(idorg);
		idevents.forEach(s->{
			System.out.println(s);
			events.add(eproxy.recupererUneEvenement(s.getId().getEvenement_id()).getContent());
		});
		return events ; 
	}
	

}
