package ��ѧ�ʼ�;



public class LianCheng {
	public static void main(String[] args){
		Matrix matrix=new Matrix();
		matrix.p=new int[]{30,35,15,5,10,20,25};
		int len=matrix.p.length;
		matrix.m=new int[len][len];
		matrix.s=new int[len][len];
		matrix.MatrixChain(matrix.p, matrix.m, matrix.s);
		System.out.println("�˷������Ŵ���");
		matrix.traceback(matrix.s, 1, len-1);
		System.out.println();
		System.out.println("mֵ��");
		matrix.showData(matrix.m);
		System.out.println("sֵ��");
		matrix.showData(matrix.s);
	}
}

class Matrix{
	
	int[] p;
	int[][] m;
	int[][] s;
	
	//���
	public void MatrixChain(int[] p,int[][] m,int[][] s){
		for(int i=1;i<=p.length-1;i++){
			m[i][i]=0;
		}
		
		for(int r=2;r<=p.length;r++){
			for(int i=1;i<=p.length-r;i++){
				int j=i+r-1;				
				//��ʼ��iΪ�ָ��
				m[i][j]=m[i][i]+m[i+1][j]+p[i-1]*p[i]*p[j];
				s[i][j]=i;
				
				//ѭ��i-j֮������зָ�㣬�ҳ�����ֵ
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
	
	//������
	public void traceback(int[][] s,int i,int j){
		if(i==j) 
			System.out.print("A"+i);    
		else if(i+1==j) 
			System.out.print(" (A"+i+" * "+" A"+j+") ");   
		else{     
			System.out.print(" (");   
			traceback(s,i,s[i][j]);   	//������
			traceback(s,s[i][j]+1,j);  	//����ұ�   
			System.out.print(") ");    
		}
	}
	
	//�������
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