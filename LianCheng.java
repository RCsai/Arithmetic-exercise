package 自学笔记;



public class LianCheng {
	public static void main(String[] args){
		Matrix matrix=new Matrix();
		matrix.p=new int[]{30,35,15,5,10,20,25};
		int len=matrix.p.length;
		matrix.m=new int[len][len];
		matrix.s=new int[len][len];
		matrix.MatrixChain(matrix.p, matrix.m, matrix.s);
		System.out.println("乘法的最优次序：");
		matrix.traceback(matrix.s, 1, len-1);
		System.out.println();
		System.out.println("m值：");
		matrix.showData(matrix.m);
		System.out.println("s值：");
		matrix.showData(matrix.s);
	}
}

class Matrix{
	
	int[] p;
	int[][] m;
	int[][] s;
	
	//求解
	public void MatrixChain(int[] p,int[][] m,int[][] s){
		for(int i=1;i<=p.length-1;i++){
			m[i][i]=0;
		}
		
		for(int r=2;r<=p.length;r++){
			for(int i=1;i<=p.length-r;i++){
				int j=i+r-1;				
				//初始以i为分割点
				m[i][j]=m[i][i]+m[i+1][j]+p[i-1]*p[i]*p[j];
				s[i][j]=i;
				
				//循环i-j之间的所有分割点，找出最优值
				for(int k=i+1;k<j;k++){
					int temp=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
					if(temp<m[i][j]){
						m[i][j]=temp;
						s[i][j]=k;
					}
				}		
			}
		}
	}
	
	//输出结果
	public void traceback(int[][] s,int i,int j){
		if(i==j) 
			System.out.print("A"+i);    
		else if(i+1==j) 
			System.out.print(" (A"+i+" * "+" A"+j+") ");   
		else{     
			System.out.print(" (");   
			traceback(s,i,s[i][j]);   	//输出左边
			traceback(s,s[i][j]+1,j);  	//输出右边   
			System.out.print(") ");    
		}
	}
	
	//输出矩阵
	public void showData(int[][] data){
		int rows=data.length;
		int columns=data[0].length;
		
		for(int i=1;i<rows;i++){
			for(int j=1;j<columns;j++){
				System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}
	}
}