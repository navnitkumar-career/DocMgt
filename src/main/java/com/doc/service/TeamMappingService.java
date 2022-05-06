package com.doc.service;

import java.util.List;
import com.doc.userdto.TeamMappingDTO;

public interface TeamMappingService {
	
	public List<TeamMappingDTO> getList();

	public TeamMappingDTO add(TeamMappingDTO teamMapping);

	void deleteById(TeamMappingDTO teamMapping);

}
