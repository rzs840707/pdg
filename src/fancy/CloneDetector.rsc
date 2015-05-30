module fancy::CloneDetector

import Prelude;
import analysis::m3::Registry;
import lang::java::m3::Core;
import lang::java::jdt::m3::Core;

import graph::DataStructures;
import graph::factory::GraphFactory;
import fancy::DataStructures;
import fancy::Seeder;
import fancy::Flow;
import fancy::Matcher;
import fancy::CloneVisualizer;
import extractors::Project;

public SystemDependence getSystemDependence(M3 projectModel, loc methodLocation) {
	node methodAST = getMethodASTEclipse(methodLocation, model = projectModel);
	return createSystemDependence(methodLocation, methodAST, projectModel, File());
}

public Candidate expandCandidate(Candidate candidate) {
	systemDependence = getSystemDependence(candidate.systemDependence.model, candidate.systemDependence.location);
	Flows flows = <createControlFs(systemDependence), createDataFs(systemDependence)>;
	
	return Candidate(systemDependence, flows, ());
}

public CandidatePairs expandRange(CandidatePairs candidates) {
	return { <first, expandCandidate(second)> | <first, second> <- candidates };
}

public CandidatePairs expandDomain(CandidatePairs candidates) {
	return { <expandCandidate(first), second> | <first, second> <- candidates };
}

public CandidatePairs expandSeeds(Projects projects, Seeds seeds) {
	unregisterProject(projects.first.location);
	unregisterProject(projects.second.location);
	
	registerProject(projects.first.location, projects.first.model);
	CandidatePairs candidatePairs = expandDomain(seeds);
	unregisterProject(projects.first.location);
	
	registerProject(projects.second.location, projects.second.model);
	candidatePairs = expandRange(candidatePairs);
	unregisterProject(projects.second.location);
	
	return candidatePairs;
}

public void findClones(str firstProjectName, str secondProjectName) {
	loc projectLocation = createProjectLoc(firstProjectName);
	ProjectData firstProject = ProjectData(projectLocation, createM3(projectLocation));
	
	projectLocation = createProjectLoc(secondProjectName);
	ProjectData secondProject = ProjectData(projectLocation, createM3(projectLocation));
	
	Projects projects = <firstProject, secondProject>;
	println("Got M3 models.");
	
	datetime before = now();
	
	Seeds seeds = generateSeeds(projects);
	println("Got the seeds.");

	CandidatePairs candidates = expandSeeds(projects, seeds);
	println("Expanded the seeds.");
	
	candidates = findMatches(candidates);
	println("Found matches.");
	
	println("Time: <createDuration(before, now()).seconds> second(s)");
	
	visualizeCloneCandidates(candidates);
}