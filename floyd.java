package 算法作业1;



public class floyd {
	static int max=100; //定义越界最大值
	static int NUM=6;
	
	//定义初始值
//	static int[][] dist={
//		{0,0,0,0,0,0,0},
//		{0,0,2,3,6,max,max},
//		{0,2,0,max,max,4,6},
//		{0,3,max,0,2,max,max},
//		{0,6,max,2,0,1,3},
//		{0,max,4,max,1,0,max},
//		{0,max,6,max,3,max,0}
//	};
	
	static int[][] route=new int[NUM+1][NUM+1];
	static int[][] dist=new int[NUM+1][NUM+1];
	
	public static void main(String[] args){
		int m=1;
		
		//初始化数据
		for(int i=1;i<=NUM;i++){
			for(int j=1;j<=NUM;j++){
				if(i!=j){
					dist[i][j]=max;
				}
				else{
					dist[i][j]=0;
				}
				route[i][j]=j;
			}
		}
		
		dist[1][2]=2;dist[1][3]=3;dist[1][4]=6;
		dist[2][1]=2;dist[2][3]=5;dist[2][4]=8;dist[2][5]=4;dist[2][6]=6;
		dist[3][1]=3;dist[3][2]=5;dist[3][4]=2;
		dist[4][1]=6;dist[4][2]=8;dist[4][3]=2;dist[4][5]=1;dist[4][6]=3;
		dist[5][2]=4;dist[5][4]=1;
		dist[6][2]=6;dist[6][4]=3;
				
		
		//插点更新最短路径值
		for(int k=1;k<=NUM;k++){
			for(int i=1;i<=NUM;i++){
				for(int j=1;j<=NUM;j++){
					if(dist[i][j]>dist[i][k]+dist[k][j]){
						dist[i][j]=dist[i][k]+dist[k][j];
						route[i][j]=route[i][k];
					}				
				}				
			}
		}
		
		//输出最短路径
		for(int i=1;i<=NUM;i++){
			String log=new String();
			int temp=0;
			for(int j=1;j<=NUM;j++){
				log=Integer.toString(i)+"->"+Integer.toString(j)+"\t"+"最短距离："+dist[i][j]+"\t";
				log=log+"最短路径为:"+Integer.toString(i)+"->";
				temp=route[i][j];
				while(temp!=j){
					log=log+Integer.toString(temp)+"->";
					temp=route[temp][j];
				}
				log=log+Integer.toString(j);
				System.out.println(log);
			}
		}
	}
}
