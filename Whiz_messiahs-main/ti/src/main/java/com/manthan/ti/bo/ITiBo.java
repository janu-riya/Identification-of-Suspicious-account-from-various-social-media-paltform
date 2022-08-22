package com.manthan.ti.bo;

import java.util.List;
import java.util.Optional;

import com.manthan.ti.entity.Data;
import com.manthan.ti.model.AnalyzeModal;
import com.manthan.ti.model.Page2Model;

public interface ITiBo {

	public Page2Model getWebsitesSocials(String searchTerm);
	public List<AnalyzeModal> analyze(Long searchTermId);
}
