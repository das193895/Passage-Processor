import java.util.ArrayList;

public class Tree {

    public TreeNode root;

    public Tree() {
        root = null;
    }

    private boolean is_leaf(TreeNode node){
        if(node == null || node.children.isEmpty() == true){
            return true;
        }
        return false;
    }


    private TreeNode findParent(TreeNode n, TreeNode child) {
        if (n == null || is_leaf(n)) {
            return null; // Child not found
        }
    
        int i = 0;
        while (i < n.s.size() && child.s.get(0).compareTo(n.s.get(i)) > 0) {
            i++;
        }
    
        if (i < n.children.size() && n.children.get(i) == child) {
            return n; // Found the parent node
        }
    
        return findParent(n.children.get(i), child); // Search down the appropriate child
    }

    private int size(TreeNode node){
        return node.s.size();
    }

    
    public void insert(String str) {
        System.out.println(str);
        // TO be completed by students
        if(root == null){
            root = new TreeNode();
            root.s.add(str);
            root.val.add(1);
        }
        else{
            TreeNode curr = root;
            while(!is_leaf(curr)){
                int i = 0;
                while(i < size(curr) && (str.compareTo(curr.s.get(i)) > 0)){
                    i++;
                    if(i == size(curr)  || str.compareTo(curr.s.get(i)) < 0){
                        break;
                    }
                }
                if (i < curr.children. size()) {
                    curr = curr.children.get(i);
                } else {
                    // Handle the case when i is out of bounds
                    break;
                }
            }
            insertNode(curr,str);
        }
    }

    private void insertNode(TreeNode node, String str) {
        int i = node.s.size() - 1;
        while (i >= 0 && str.compareTo(node.s.get(i)) < 0) {
            i--;
        }
        i++;
        node.s.add(i, str);
        node.val.add(i,1);
       
        // Split the node if it has 4 values
        if (node.s.size() == 4) {
            TreeNode newnode = new TreeNode();
            newnode.s.add(node.s.get(2));
            newnode.val.add(node.val.get(2));
            newnode.s.add(node.s.get(3));
            newnode.val.add(node.val.get(3));
            String middle = node.s.get(1);
            int middle_val = node.val.get(1);
        
            if(node.children != null && node.children.size() > 0){
                int fl = 0;
                for(int q = 0;q<node.children.size();q++){
                    for(int a = 0;a<node.children.get(q).s.size();a++){
                        if (str.equals(node.children.get(q).s.get(a))){
                            fl = fl + q;
                        }
                    }
                }
    
                if (fl == 3){

                    newnode.children.add(0,node.children.get(2));
                    newnode.children.add(1,node.children.get(3));
                    newnode.children.add(2,node.children.get(4));

                    newnode.children.get(1).s.remove(3);
                    newnode.children.get(1).val.remove(3);
                    newnode.children.get(1).s.remove(2);
                    newnode.children.get(1).val.remove(2);
                    newnode.children.get(1).s.remove(1);
                    newnode.children.get(1).val.remove(1);

                    node.children.remove(4);
                    node.children.remove(3);
                    node.children.remove(2);

                }

                if(fl == 2){
                    newnode.children.add(0,node.children.get(2));
                    newnode.children.add(1,node.children.get(4));
                    newnode.children.add(2,node.children.get(3));

                    newnode.children.get(0).s.remove(3);
                    newnode.children.get(0).val.remove(3);
                    newnode.children.get(0).s.remove(2);
                    newnode.children.get(0).val.remove(2);
                    newnode.children.get(0).s.remove(1);
                    newnode.children.get(0).val.remove(1);

                    if(node.children != null && node.children.size() > 0){
                        node.children.remove(4);
                        node.children.remove(3);
                        node.children.remove(2);

                    }
                }
                if(fl == 1){
                    newnode.children.add(0,node.children.get(4));
                    newnode.children.add(1,node.children.get(2));
                    newnode.children.add(2,node.children.get(3));

                    if(node.children != null && node.children.size() > 0){
                        node.children.remove(4);
                        node.children.remove(3);
                        node.children.remove(2);
                        node.children.get(1).s.remove(3);
                        node.children.get(1).val.remove(3);
                        node.children.get(1).s.remove(2);
                        node.children.get(1).val.remove(2);
                        node.children.get(1).s.remove(1);
                        node.children.get(1).val.remove(1);
                    }
                }
                if(fl == 0){
                    newnode.children.add(0,node.children.get(1));
                    newnode.children.add(1,node.children.get(2));
                    newnode.children.add(2,node.children.get(3));

                    if(node.children != null && node.children.size() > 0){
                        
                    node.children.remove(3);
                    node.children.remove(2);
                    node.children.remove(1);

                    node.children.get(0).s.remove(3);
                    node.children.get(0).val.remove(3);
                    node.children.get(0).s.remove(2);
                    node.children.get(0).val.remove(2);
                    node.children.get(0).s.remove(1);
                    node.children.get(0).val.remove(1);
                    }
                }
            }
            TreeNode par = findParent(root, node);
         
            if(par == null){
                par = new TreeNode();
                par.s.add(middle);
                par.val.add(middle_val);
                node.s.remove(3);
                node.val.remove(3);
                node.s.remove(2);
                node.val.remove(2);
                node.s.remove(1);
                node.val.remove(1);
                par.children.add(0,node);
                par.children.add(1,newnode);
                root = par;
            }
            else if (par.s.size() == 1 || par.s.size() == 2){
                int k = par.s.size() - 1;
                while(k >= 0 && middle.compareTo(par.s.get(k)) < 0){
                    k--;
                }
                par.s.add(k+1,middle);
                par.val.add(k+1,middle_val);
                par.children.add(k+2,newnode);
                node.s.remove(3);
                node.val.remove(3);
                node.s.remove(2);
                node.val.remove(2);
                node.s.remove(1);
                node.val.remove(1);
            }

            else if (par.s.size() == 3){
                //System.out.println("6778");
                par.children.add(4,newnode);
                insertNode(par,middle);
            } 
        }
    }

   

    public boolean delete(String s) {
        // TO be completed by students
        return false;
    }


    public boolean search(String s) {
        // TO be completed by students
        return search1(this.root,s);
    }


    private boolean search1(TreeNode node, String str) {
        if (node == null) {
            return false;
        }
        int i = 0;
        while (i < node.s.size()) {
            if (node.s.get(i).equals(str)) {
                return true;
            }
            i++;
        }
        int j = 0;
        while (j < node.s.size() && str.compareTo(node.s.get(j)) > 0) {
            j++;
        }
        if (j == node.s.size()) {
            if (j < node.children.size()) {
                node = node.children.get(j);
            } else {
                return false;
            }
        } else if (str.equals(node.s.get(j))) {
            return true;
        } else {
            if (j < node.children.size()) {
                node = node.children.get(j);
            } else {
                return false;
            }
        }
        return search1(node, str);
    }

    public int getHeight() {
        return getHeight1(root);
    }
    
    private int getHeight1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxHeight = 0;
        for (TreeNode child : node.children) {
            int childHeight = getHeight1(child);
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight + 1;
    }


    public int getVal(String s) {
        // TO be completed by students
        return getVal1(this.root,s);
    }



    private int getVal1(TreeNode node, String str) {
        int i = 0;
        while (i < node.s.size()) {
            if (node.s.get(i).equals(str)) {
                return node.val.get(i);
            }
            i++;
        }
        int j = 0;
        while (j < node.s.size() && str.compareTo(node.s.get(j)) > 0) {
            j++;
        }
        if (j == node.s.size()) {
            if (j < node.children.size()) {
                return getVal1(node.children.get(j), str);
            }
        } else if (str.equals(node.s.get(j))) {
            return node.val.get(j);
        } else {
            if (j < node.children.size()) {
                return getVal1(node.children.get(j), str);
            }
        }
        // return 1 if the string is not found
        return 1;
    }


    
    private int increment1(TreeNode node, String str) {
        int i = 0;
        while (i < node.s.size()) {
            if (node.s.get(i).equals(str)) {
                int f = node.val.get(i);
                node.val.set(i, f + 1);
                return node.val.get(i);
            }
            i++;
        }
        int j = 0;
        while (j < node.s.size() && str.compareTo(node.s.get(j)) > 0) {
            j++;
        }
        if (j == node.s.size()) {
            if (j < node.children.size()) {
                // recursively call increment on the appropriate child node
                return increment1(node.children.get(j), str);
            }
        } else if (str.equals(node.s.get(j))) {
            int f = node.val.get(j);
            node.val.set(j, f + 1);
            return node.val.get(j);
        } else {
            if (j < node.children.size()) {
                // recursively call increment on the appropriate child node
                return increment1(node.children.get(j), str);
            }
        }
        // return 1 if the string is not found
        return 1;
    }

    public int increment(String s) {
        // TO be completed by students
        return increment1(this.root,s);
    }


    private int decrement1(TreeNode node, String str) {
        int curr = getVal(str);
        if(curr == 1){
            return 1;
        }
        int i = 0;
        while (i < node.s.size()) {
            if (node.s.get(i).equals(str)) {
                int f = node.val.get(i);
                node.val.set(i, f - 1);
                return node.val.get(i);
            }
            i++;
        }
        int j = 0;
        while (j < node.s.size() && str.compareTo(node.s.get(j)) > 0) {
            j++;
        }
        if (j == node.s.size()) {
            if (j < node.children.size()) {
                // recursively call increment on the appropriate child node
                return decrement1(node.children.get(j), str);
            }
        } else if (str.equals(node.s.get(j))) {
            int f = node.val.get(j);
            node.val.set(j, f - 1);
            return node.val.get(j);
        } else {
            if (j < node.children.size()) {
                // recursively call increment on the appropriate child node
                return decrement1(node.children.get(j), str);
            }
        }
        // return 1 if the string is not found
        return 1;
    }

    public int decrement(String s) {
        // TO be completed by students
        return decrement1(this.root,s);
    }

   
}