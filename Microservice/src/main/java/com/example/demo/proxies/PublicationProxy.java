package com.example.demo.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.PublicationBean;

@FeignClient(value = "publicationservice")
@RibbonClient(name = "publicationservice")

public interface PublicationProxy {
	@GetMapping(value = "/publications")
	CollectionModel<PublicationBean> listeDesPublications();

	@GetMapping( value = "/publications/{id}")
	EntityModel<PublicationBean> recupererUnePublication(@PathVariable("id") Long id);
}
