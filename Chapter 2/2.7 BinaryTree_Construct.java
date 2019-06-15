import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeNode {
	public static TreeNode construct(int[] preorder, int[] inorder) {
		if(preorder==null || inorder==null)
			return(null);
		if(preorder.length==0 || inorder.length==0)
			return(null);
		if(preorder.length != inorder.length)
			return(null);
		TreeNode root = new TreeNode(preorder[0]);
		for(int i=0; i<preorder.length; i++) {
			if(preorder[0] == inorder[i]) {
				root.left = construct(Arrays.copyOfRange(preorder, 1, i+1), 
						Arrays.copyOfRange(inorder, 0, i));
				root.right = construct(Arrays.copyOfRange(preorder, i+1, preorder.length),
						Arrays.copyOfRange(inorder, i+1, inorder.length));
			}
		}
		return(root);
	}
	
	//按层打印二叉树
	public static void Print(TreeNode pRoot){
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		depth(pRoot, 1, list);
		System.out.println(list);
	}
	
	//用递归，二叉树遍历
	private static void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
		if(root==null)
			return;
		if(depth > list.size())
			list.add(new ArrayList<Integer>());
		list.get(depth-1).add(root.val);
		
		depth(root.left, depth+1, list);
		depth(root.right, depth+1, list);
	}
	
	public static void main(String[] args) {
		int[] preorder = {1,2,4,7,3,5,6,8};
		int[] inorder = {4,7,2,1,5,3,8,6};
		TreeNode tree = construct(preorder, inorder);
		Print(tree);
	}
}
