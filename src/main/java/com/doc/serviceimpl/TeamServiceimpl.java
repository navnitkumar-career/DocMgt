package com.doc.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.entities.Team;
import com.doc.repository.TeamRepository;
import com.doc.service.TeamService;
import com.doc.userdto.TeamDTO;

@Service
public class TeamServiceimpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	@Override
	public List<TeamDTO> getList() {

		List<Team> teamList = teamRepository.findAll();
		List<TeamDTO> team = teamList.stream().map(t -> {
			TeamDTO teams = new TeamDTO();

			modelMapper.map(t, teams);
			return teams;
		}).collect(Collectors.toList());
		return team;
	}

	
	@Transactional
	@Override
	public boolean add(TeamDTO teamAdd) {
		List<Team> teamList = teamRepository.findAll();
		boolean flag = true;

		if(teamList != null) {
			for (Team team : teamList) {
				if (team.getTeamName().toLowerCase().equals(teamAdd.getTeamName().toLowerCase())) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		}
		
		if (flag) {
			Team team = new Team();
			modelMapper.map(teamAdd, team);
			teamRepository.save(team);
			modelMapper.map(team, teamAdd);
			return flag;
		}
		return flag;
	}

	@Transactional
	@Override
	public boolean update(TeamDTO teamEdit) {
		List<Team> teamList = teamRepository.findAll();
		boolean flag = false;

		for (Team team : teamList) {
			if (team.getTeamName().equals(teamEdit.getTeamName())) {
				flag = false;
			} else {
				flag = true;
			}
		}
		if (flag) {
			Team team1 = teamRepository.findById(teamEdit.getId()).get();
			modelMapper.map(teamEdit, team1);

			teamRepository.save(team1);
			modelMapper.map(team1, teamEdit);
		}
		return flag;
	}

	@Override
	public boolean deleteById(String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		if (team == null) {
			return false;
		} else {
			teamRepository.deleteById(team.getId());
			return true;
		}
	}
}
