module screen::SystemDependenceScreen

import Prelude;
import lang::java::jdt::m3::Core;
import analysis::graphs::Graph;
import vis::Figure;
import vis::Render;
import vis::KeySym;
import util::Editors;
import lang::java::m3::AST;

import screen::Screen;
import extractors::Project;
import graph::system::SDG;
import graph::DataStructures;
import graph::factory::GraphFactory;


@doc {
	To run a test:
		displaySystemDependenceGraph(|project://JavaTest|, "main");
}
public void displaySystemDependenceGraph(loc project, str methodName) {
	M3 projectModel = createM3(project);
	loc methodLocation = getMethodLocation(methodName, projectModel);
	node methodAST = getMethodASTEclipse(methodLocation, model = projectModel);
		
	ControlDependences controlDependences = createControlDependences(methodLocation, methodAST, projectModel, true);
	DataDependences dataDependences = createDataDependences(methodLocation, methodAST, projectModel, true);
	SystemDependence systemDependence = createSDG(controlDependences, dataDependences);
	
	list[Edge] edges = createEdges(systemDependence.controlDependence, "solid", "blue")
		+ createEdges(systemDependence.dataDependence, "dash", "green")
		+ createEdges(systemDependence.iControlDependence, "solid", "deepskyblue")
		+ createEdges(systemDependence.iDataDependence, "dash", "lime");
	
	list[Figure] boxes = ([] | it + createSDGBoxes(method) | method <- controlDependences);
	
	render(graph(boxes, edges, hint("layered"), gap(50)));
}

public list[Edge] createEdges(Graph[str] graph, str style, str color) {
	return [ edge(graphEdge.from, graphEdge.to, 
					lineStyle(style), lineColor(color), toArrow(box(size(10), 
					fillColor(color)))) | graphEdge <- graph ];
}

private str getBoxColor(NodeType nodeType) {
	switch(nodeType) {
		case Normal(): return "lightgreen";
		case CallSite(): return "lightpink";
		case Parameter(): return "beige";
	}
}

public Figures createSDGBoxes(MethodData methodData) {
	return [ box(
				text("<methodData.name>:<treeNode>"), 
				id(encodeVertex(methodData, treeNode)), 
				size(50), 
				fillColor(getBoxColor(resolveIdentifier(methodData, treeNode)@nodeType)), 
				onMouseDown(
					goToSource(
						getLocation(
							resolveIdentifier(methodData, treeNode)
						)
					)
				)
			) | treeNode <- environmentDomain(methodData) ]
			+ box(text("ENTRY <methodData.name>"), id(encodeVertex(methodData, ENTRYNODE)), size(50), fillColor("lightblue"));
}