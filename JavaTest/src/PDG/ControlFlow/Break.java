package PDG.ControlFlow;

public class Break {
	/*
	 * PDT: { <EXITNODE,ENTRYNODE>, <EXITNODE, 1>, <1,4>, <1,0>, <0,STARTNODE>, <EXITNODE, 3>, <EXITNODE, 2> }
	 */
	public void testBreak1(){
		int i = 0; // 0
		
		while(i < 10) { // 1
			if(i == 6) { // 2
				break; // 3
			}
			
			i = 10; // 4
		}
	}
	
	/*
	 * PDT: { <EXITNODE,ENTRYNODE>, <EXITNODE, 5>, <5,3>, <5,1>, <1,4>, <5,2>, <1,0>, <0, STARTNODE> }
	 */
	public void testBreak2(){
		int i = 0; // 0
		
		while(i < 10) { // 1
			if(i == 6) { // 2
				break; // 3
			}
			
			i = 10; // 4
		}
		
		i = i * 10; // 5
	}
	
	/*
	 * PDT: { <EXITNODE,ENTRYNODE>, <EXITNODE, 1>, <6,2>, <6,3>, <6,4>, <2,5>, <1,6>, <1,0>, <0, STARTNODE> }
	 */
	public void testBreak3(){
		int i = 0; // 0
		
		while(i < 10) { // 1
			while (i < 7) { // 2
				if(i == 6) { // 3
					break; // 4
				}
				
				i = 5; // 5
			}
			
			i = 10; // 6
		}
	}
	
	/*
	 * PDT: { <EXITNODE,ENTRYNODE>, <EXITNODE, 7>, <7,1>, <6,2>, <6,3>, <6,4>, <2,5>, <1,6>, <1,0>, <0, STARTNODE> }
	 */
	public void testBreak4(){
		int i = 0; // 0
		
		while(i < 10) { // 1
			while (i < 7) { // 2
				if(i == 6) { // 3
					break; // 4
				}
				
				i = 5; // 5
			}
			
			i = 10; // 6
		}
		
		i = i * 10; // 7
	}
}
