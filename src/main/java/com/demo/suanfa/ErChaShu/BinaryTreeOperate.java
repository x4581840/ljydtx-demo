package com.demo.suanfa.ErChaShu;

public class BinaryTreeOperate {

    /**
     * 翻转二叉树（左子树和右子树 调换位置）
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0, "root");
        TreeNode root_left = new TreeNode(1, "root_left");

//        TreeNode root_left_l = new TreeNode(2, "root_left_l");
//        TreeNode root_left_r = new TreeNode(3, "root_left_r");

        TreeNode root_right = new TreeNode(4, "root_right");

//        TreeNode root_right_l = new TreeNode(5, "root_right_l");
//        TreeNode root_right_r = new TreeNode(6, "root_right_r");

        root.left = root_left;
        root.right = root_right;
//
//        root_left.left = root_left_l;
//        root_left.right = root_left_r;
//
//        root_right.left = root_right_l;
//        root_right.right = root_right_r;

        invertTree(root);

        System.out.println(root.left.name);
        System.out.println(root.right.name);

//        System.out.println(root_left.left.name);
//        System.out.println(root_left.right.name);
//
//        System.out.println(root_right.left.name);
//        System.out.println(root_right.right.name);
    }
}
