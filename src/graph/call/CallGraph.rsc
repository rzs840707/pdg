module graph::call::CallGraph

import Prelude;
import lang::java::m3::AST;
import lang::java::m3::Core;
import lang::java::jdt::m3::Core;
import analysis::m3::Registry;
import analysis::graphs::Graph;

import extractors::Project;
import graph::DataStructures;


private map[CallGraph, map[str, str]] methodToFile = ();
private map[CallGraph, map[str, set[str]]] fileToMethods = ();

private str getVertexName(loc location) {
	return "<location.parent.file>:<location.file>";
}

public CallGraph createCG(M3 projectModel, loc projectLocation) {
	Graph[str] callGraph = {};
	set[loc] projectMethods = getM3Methods(projectModel);
	
	map[str, str] methodFileMapping = ();
	map[str, set[str]] fileMethodsMapping = ();
	
	map[str, set[str]] methodCalls = ();
	map[str, loc] locations = ();
	
	for(method <- projectMethods) {
		methodCalls[getVertexName(method)] = {};
		locations[getVertexName(method)] = method;
		
		if(method.parent.file in fileMethodsMapping) {
			fileMethodsMapping[method.parent.file] += { getVertexName(method) };
		} else {
			fileMethodsMapping[method.parent.file] += {};
		}
		
		methodFileMapping[getVertexName(method)] = method.parent.file;
	}
	
	for(<caller, callee> <- projectModel@methodInvocation, caller in projectMethods, callee in projectMethods) {
		str methodVertex = getVertexName(caller);
		str calledVertex = getVertexName(callee);
			
		callGraph += { <methodVertex, calledVertex> };
		methodCalls[methodVertex] += { calledVertex };
	}
	
	CallGraph result = CallGraph(callGraph, locations, methodCalls);
	methodToFile[result] = methodFileMapping; 
	fileToMethods[result] = fileMethodsMapping;
	
	return result;
}

public str getMethodFile(CallGraph callgraph, str method) {
	return methodToFile[callgraph][method];
}

public set[str] getFileMethods(CallGraph callgraph, str file) {
	return fileToMethods[callgraph][file];
}