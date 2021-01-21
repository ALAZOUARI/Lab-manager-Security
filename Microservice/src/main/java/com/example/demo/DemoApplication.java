package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Role;
import com.example.demo.enumeration.RoleName;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DemoApplication implements CommandLineRunner {
	@Autowired
	MembreRepository memberRepository;
	@Autowired
	IMemberService iMemberService;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	PublicationProxy publicationProxy;
 
	@Autowired
	RoleRepository roleRepository; 

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run(String... args) throws Exception {

		EnseignantChercheur ens1 = new EnseignantChercheur("01752354", "Jmaiel", "Mohamed", new Date(), "gg","jmaiel@enis.tn", "0000", "ENIS", "Professeur");
		memberRepository.save(ens1);

		Membre ens2 = new EnseignantChercheur("01752354", "mariam", "lahami", new Date(), "", "lahami@enis.tn", "2222","ENIS", "MA");

		memberRepository.save(ens2);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = dateFormatter.parse("2010-05-01");
		Date date2 = dateFormatter.parse("2019-05-01");
		Date date3 = dateFormatter.parse("2012-05-01");
		Date date4 = dateFormatter.parse("2012-03-01");
		Etudiant etd1 = new Etudiant("081705454", "ben fekih", "rim", date1, null,"dd", "rim@enis.rn", "11111", date1, "test", null);
		Etudiant etd2 = new Etudiant("885705454", "ben ahmed", "sana", date2, null, "ddg","sana@enis.rn", "11111", date2,"test", null);
		Etudiant etd3 = new Etudiant("081454", "chaari", "rim", date3, null,"gg", "chaari@enis.rn", "11111", date3, "test", null);
		Etudiant etd4 = new Etudiant("0814541", "ayadi", "ali", date4,null, "", "ayadi@enis.rn", "11111", date4, "test", null);
		memberRepository.save(etd1);
		memberRepository.save(etd2);
		memberRepository.save(etd3);
		memberRepository.save(etd4);

		// affecter un étduiant à un enseigant
		iMemberService.affecterencadrantToetudiant(5L, 1L);
		iMemberService.affecterencadrantToetudiant(6L, 1L);
		iMemberService.affecterencadrantToetudiant(3L, 1L);
		
		
		

		// find etudiants encadré par 1

		List<Etudiant> etds = etudiantRepository.findByEncadrant(ens1);
		System.out.print(etds.size());

		// affecter une publication à un auteur

		// 1-récupérer la publication par id en invoquant publication-service
		PublicationBean pub1 = publicationProxy.recupererUnePublication(1L).getContent();
		System.out.println(pub1.getTitre() + "  " + pub1.getId());
		PublicationBean pub2 = publicationProxy.recupererUnePublication(2L).getContent();
		System.out.println(pub2.getTitre() + "  " + pub2.getId());

		// 2- affecter pub à member
		iMemberService.affecterauteurTopublication(1L, pub1.getId());
		iMemberService.affecterauteurTopublication(2L, pub1.getId());
		iMemberService.affecterauteurTopublication(1L, pub2.getId());

		// afficher le nombre de publication du membre 1
		List<PublicationBean> lstpubs = iMemberService.findPublicationparauteur(1L);
		lstpubs.forEach(r -> System.out.println(r.toString()));

		PublicationBean p = publicationProxy.recupererUnePublication(1L).getContent();
		System.out.println(p); 
		Role role1 =new Role(RoleName.ROLE_ADMIN);
		Role role2=new Role(RoleName.ROLE_USER);
		roleRepository.save(role1);
		roleRepository.save(role2);
		
	}

}