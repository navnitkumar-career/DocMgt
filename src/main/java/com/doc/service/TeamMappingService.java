package com.doc.service;

import java.util.List;
import com.doc.userdto.TeamMappingDTO;

public interface TeamMappingService {
	
	public List<TeamMappingDTO> getList();

	public TeamMappingDTO Add(TeamMappingDTO teamMapping);

	void DeleteById(TeamMappingDTO teamMapping);

}
