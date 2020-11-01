package 自学笔记;

import java.text.DecimalFormat;


public class oBST {
	public static void main(String[] args){
		BST bst=new BST();
		bst.p=new double[]{0.00,0.15,0.10,0.05,0.10,0.20};
		bst.q=new double[]{0.05,0.10,0.05,0.05,0.05,0.10};
		int len=bst.p.length;
		bst.E=new double[len+1][len+1];
		bst.W=new double[len+1][len+1];
		bst.R=new double[len+1][len+1];
		
		bst.optimalBST(len, bst.p, bst.q, bst.E, bst.W, bst.R);
		
		System.out.println("E:");
		bst.showData(bst.E);
		
		System.out.println("W:");
		bst.showData(bst.W);
		
		System.out.println("R:");
		bst.showData(bst.R);
	}
}


class BST{
	double[] p;
	double[] q;
	double[][] E;
	double[][] W;
	double[][] R;
	
	
	//求解
	public void optimalBST(int n,double[] p,double[] q,double[][] E,double[][] W,double[][] R){
		//初始化
		for(int i=1;i<=n;i++){
			E[i][i-1]=q[i-1];
			W[i][i-1]=q[i-1];
			R[i][i-1]=0;
		}
		
		//按照对角线逐条进行计算
		for(int d=1;d<=n;d++){
			for(int i=1;i<=n-d;i++){
				int j=i+d-1;
				E[i][j]=10000;
				W[i][j]=W[i][j-1]+p[j]+q[j];
				for(int k=i;k<=j;k++){
					double temp=E[i][k-1]+E[k+1][j]+W[i][j];
					if(temp<E[i][j]){
						E[i][j]=temp;
						R[i][j]=k;
					}
				}
			}
		}
	}
	
	//输出数据
	public void showData(double[][] data){
		int rows=data.length;
		int columns=data[0].length;
		DecimalFormat dFormat=new DecimalFormat("#.00");
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				System.out.print(dFormat.format(data[i][j])+"\t");
			}
			System.out.println();
		}
	}
}