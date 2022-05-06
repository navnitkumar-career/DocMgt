package com.doc.service;

import java.util.List;

import com.doc.userdto.TeamDTO;

public interface TeamService {
	
	public List<TeamDTO> getList();

	public boolean  Update(TeamDTO team);

	public boolean Add(TeamDTO team);

	public boolean DeleteById(String teamName);
	
}
