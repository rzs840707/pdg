package PDG.ControlFlow;

public class Switch {
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,7>, <7,6>, <6,5>, <5,4>, <4,3>, <3,2>, <6,1>, <1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <ENTRYNODE,6>, <ENTRYNODE,7> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch1(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
				i = 3; // 5
			default: // 6 
				i = 4; // 7
		}
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,8>, <8,7>, <7,6>, <6,5>, <5,4>, <4,3>, <3,2>, <6,1>, <1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <ENTRYNODE,6>, <ENTRYNODE,7>, <ENTRYNODE, 8> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch2(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
				i = 3; // 5
			default: // 6 
				i = 4; // 7
		}
		
		i = 5; // 8
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,10>, <10,9>, <9,8>, <EXITNODE,7>, <7,6>, <6,5>, <EXITNODE,4>, 
	 * 			<4,3>, <3,2>, <EXITNODE,1>, <1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7>, <1,8>, <1,9>, <1,10> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch3(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2
				i = 2; // 3
				break; // 4
			case 1: // 5
				i = 3; // 6
				break; // 7
			default: // 8
				i = 4; // 9
				break; // 10
		}
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,11>, <11,10>, <11,7>, <11,4>, <11,1>, <10,9>, <9,8>, 
	 * 			<7,6>, <6,5>, <4,3>, <3,2>, <1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7>, <1,8>, <1,9>, <1,10>, <ENTRYNODE, 11> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch4(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2
				i = 2; // 3
				break; // 4
			case 1: // 5
				i = 3; // 6
				break; // 7
			default: // 8
				i = 4; // 9
				break; // 10
		}
		
		i = 5; // 11
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,9>, <9,8>, <8,7>, <7,6>, <6,5>, <4,3>, <3,2>, 
	 * 			<1,0>, <EXITNODE,4>, <EXITNODE,1>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7>, <1,8>, <1,9> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch5(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2
				i = 2; // 3
				break; // 4
			case 1: // 5
				i = 3; // 6
			default: // 7
				i = 4; // 8
				break; // 9
		}	
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE,10>, <10,9>, <9,8>, <8,7>, <7,6>, <6,5>, <4,3>, <3,2>, 
	 * 			<1,0>, <10,4>, <10,1>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7>, <1,8>, <1,9>, <ENTRYNODE, 10> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch6(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2
				i = 2; // 3
				break; // 4
			case 1: // 5
				i = 3; // 6
			default: // 7
				i = 4; // 8
				break; // 9
		}
		
		i = 5; // 10
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE, 7>, <7,6>, <6,5>, <5,4>, <4,3>, <3,2>, <EXITNODE,1>,
	 * 			<1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch7(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
				i = 3; // 5
			case 2: // 6 
				i = 4; // 7
		}
	}
	
	/*
	 * PDT: { <EXITNODE, ENTRYNODE>, <EXITNODE, 8>, <8,7>, <7,6>, <6,5>, <5,4>, <4,3>, <3,2>, <8,1>,
	 * 			<1,0>, <0,STARTNODE> }
	 * CDG: { <ENTRYNODE, 0>, <ENTRYNODE, 1>, <1,2>, <1,3>, <1,4>, <1,5>, <1,6>, <1,7>, <ENTRYNODE, 8> }
	 * DDG: { <0,1> }
	 */
	public void testSwitch8(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
				i = 3; // 5
			case 2: // 6 
				i = 4; // 7
		}
		
		i = 5; // 8
	}
	
	public void testSwitch9(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
			case 2: // 6 
				i = 4; // 7
		}
		
		i = 5; // 8
	}
	
	public void testSwitch10(){
		int i = 0; // 0
		
		switch(i) { // 1
			case 0: // 2 
				i = 2; // 3
			case 1: // 4 
				try {
					i = i + i;
					
					if(i == 10) {
						break;
					}
				} catch(Exception exception) {
					i = -5;
				}
			case 2: // 6 
				i = 4; // 7
		}
		
		i = 5; // 8
	}
}
