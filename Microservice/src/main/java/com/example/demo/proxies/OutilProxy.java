package com.example.demo.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.OutilBean;

@FeignClient(value = "outilservice")
@RibbonClient(name = "outilservice")
public interface OutilProxy {
	@GetMapping(value = "/outils")
	CollectionModel<OutilBean> listeDesoutils();

	@GetMapping(value = "/outils/{id}")
	EntityModel<OutilBean> recupererUneoutil(@PathVariable("id") Long id);

}
