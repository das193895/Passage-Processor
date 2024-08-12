import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Application extends Tree {

    private int size(TreeNode node){
        return node.s.size();
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




public void insert(String str) {
    // TO be completed by students
    if(root == null){
        root = new TreeNode();
        root.s.add(str);
        root.val.add(1);
        root.max_value = maximum_v(root);
        root.count = count_c(root);


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
        insertNode(curr,str,1);
    }
}

private void insertNode(TreeNode node, String str , int value) {
    int i = node.s.size() - 1;
    while (i >= 0 && str.compareTo(node.s.get(i)) < 0) {
        i--;
    }
    i++;
    node.s.add(i, str);
    node.val.add(i,value);
    node.max_value = maximum_v(node);
    node.count = count_c(node);

    TreeNode parent = findParent(root,node);
    if(parent != null){
        parent.max_value = maximum_v(parent);
        parent.count = count_c(parent);
    }
    
   
    // Split the node if it has 4 values
    if (node.s.size() == 4) {
        TreeNode newnode = new TreeNode();
        newnode.s.add(node.s.get(2));
        newnode.val.add(node.val.get(2));
       
        newnode.s.add(node.s.get(3));
        newnode.val.add(node.val.get(3));

        newnode.max_value = maximum_v(newnode);
        newnode.count = count_c(newnode);

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
                newnode.children.get(1).max_value = maximum_v(newnode.children.get(1));
                newnode.children.get(1).count = count_c(newnode.children.get(1));

                newnode.count = count_c(newnode);
                newnode.max_value = maximum_v(newnode);

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
                newnode.children.get(0).max_value = maximum_v(newnode.children.get(0));
                newnode.children.get(0).count = count_c(newnode.children.get(0));

                newnode.count = count_c(newnode);
                newnode.max_value = maximum_v(newnode);

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
                    node.children.get(1).max_value = maximum_v(node.children.get(1));
                    node.children.get(1).count = count_c(node.children.get(1));

                }

                newnode.count = count_c(newnode);
                newnode.max_value = maximum_v(newnode);


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
                node.children.get(0).max_value = maximum_v(node.children.get(0));
                node.children.get(0).count = count_c(node.children.get(0));

                }

                newnode.count = count_c(newnode);
                newnode.max_value = maximum_v(newnode);
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
            node.count = count_c(node);
            node.max_value = maximum_v(node);
            par.children.add(0,node);
            par.children.add(1,newnode);
            par.max_value = maximum_v(par);
            par.count = count_c(par);
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
            node.max_value = maximum_v(node);
            node.count = count_c(node);
            par.max_value = maximum_v(par);
            par.count = count_c(par);
        }

        else if (par.s.size() == 3){
            //System.out.println("6778");
            par.children.add(4,newnode);
            // par.max_value = maximum_v(par);
            // par.count = count_c(par);

            insertNode(par,middle,middle_val);
        } 
    }
}

    private int increment2(TreeNode node, String str) {
       
        int j = 0;
        while (j < node.s.size() && str.compareTo(node.s.get(j)) > 0) {
            j++;
        }
        if (j == node.s.size()) {
            if (j < node.children.size()) {
               
                return increment2(node.children.get(j), str);
            }
        } else if (str.equals(node.s.get(j))) {
            int f = node.val.get(j);
            node.val.set(j, f + 1);
            TreeNode parent = findParent(this.root,node);
            if(parent != null){
                parent.count = count_c(parent);
                
            }
            node.count = count_c(node);
            node.max_value = maximum_v(node);
            //System.out.println(node.children.get(0).count+"tytytyty");
            return node.val.get(j);
        } else {
            if (j < node.children.size()) {
                // recursively call increment on the appropriate child node
                //node.children.get(j).count = count_c(node.children.get(j));
                
                return increment2(node.children.get(j), str);
            }
          
        }
        for(int t = 0;t<node.children.size();t++){
            node.children.get(t).count = count_c(node.children.get(t));

        }
        node.count = count_c(node);
        node.max_value = maximum_v(node);
        // return 1 if the string is not found
        return 1;
    }
    
   

    private int maximum_v(TreeNode node){
        int maxi = Integer.MIN_VALUE;
        for(int i = 0;i<node.val.size();i++){
            maxi = Math.max(maxi,node.val.get(i));
        }
        return maxi;
    }



    private int count_c(TreeNode node){
        if(node == null){
            return 0 ;
        }
        if(node != null && node.children == null && node.children.size() > 0){
            int sum = 0;
            for(int i = 0 ;i<node.val.size();i++){
                sum = sum + node.val.get(i);
            }
            node.count = sum;
            return sum;
        }

        if(node != null && node.children != null){
            int s = 0;
            for(int i = 0;i<node.val.size();i++){
                s = s + node.val.get(i);
                //System.out.println(s+"tttt");
            }

            int ss = 0;
            for(int i = 0;i<node.children.size();i++){
                ss = ss + count_c(node.children.get(i));
            }
            node.count = ss+s;
            return ss + s;
        }
        return 0;
    }

    public int increment(String s){
        // TO be completed by students
        return increment2(root,s);
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

public void buildTree(String fileName){
    // TO be completed by students

    File file = new File(fileName);

    try {

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {

            
            
            //String data = sc.nextLine();
            String l = sc.nextLine();
            String s = "";
            //System.out.println(l);

            for(int i = 0;i<l.length();i++){

                if(l.charAt(i) == ' '){
                    boolean f = search(s);
                    if(f == false){
                        insert(s);
                    }
                    else{
                        increment(s);
                    }
                 
                    s = s.replaceAll("([a-z])","");
                   

                }
                else{
                    s = s+l.charAt(i);
                }
            }
            if(l.charAt(l.length()-1) != ' '){
                boolean f = search(s);
                if(f == false){
                    insert(s);
                }
                else{
                    increment(s);
                }
            }
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        System.out.println(e);
    }
}
    private TreeNode LCA(String str1 , String str2){
       // System.out.println(root.s.get(0));
        TreeNode curr = root;

        while (curr != null){
            boolean f1 = false;
            boolean f2 = false;

            for(int i = 0 ;i<curr.s.size();i++){
                if(curr.s.get(i).equals(str1)){
                    f1 = true;
                    //break;
                }
            }

            for(int i = 0;i<curr.s.size();i++){
                if(curr.s.get(i).equals(str2)){
                    f2 = true;
                    //break;
                }
            }

            if(f1 == true && f2 == true){
                return curr;
            }

            else if (str2.compareTo(curr.s.get(0)) < 0 ) {
                curr = curr.children.get(0);
            }
            else if(str1.compareTo(curr.s.get(curr.s.size()-1)) > 0){
                curr = curr.children.get(curr.s.size());
            }
            else if (str1.compareTo(curr.s.get(0)) < 0 && str2.compareTo(curr.s.get(curr.s.size() - 1)) > 0 ) {
                return curr;
            }
            else {
                for (int i = 0; i < curr.s.size(); i++) {
                    if (str1.compareTo(curr.s.get(i)) < 0 && str2.compareTo(curr.s.get(i)) > 0) {
                        return curr;
                    } else if (str1.compareTo(curr.s.get(i)) < 0 ) {
                        curr = curr.children.get(i);
                        break;
                    }
                }
            }
        }
        return null;
    }



    public int cumulativeFreq(String s1, String s2){
        // TO be completed by students

        TreeNode lca = LCA(s1,s2);
        int final_a = lca.count;
       
        int i = lca.s.size() - 1;

        while (i >= 0 && s1.compareTo(lca.s.get(i)) < 0) {
            i--;
        }

        if(i == 0){
            final_a = final_a - (lca.val.get(0));
            if(lca.children != null && lca.children.size() > 0){
                final_a = final_a - (lca.children.get(0).count);
            }
        }

        else if (i == 1){
            final_a = final_a - (lca.val.get(0) + lca.val.get(1));
            if(lca.children != null && lca.children.size() > 0){
                final_a = final_a - (lca.children.get(0).count + lca.children.get(1).count);
            }
        }

        int j = 0;

        while(j<lca.s.size() &&  s2.compareTo(lca.s.get(j)) > 0 ){
            j++;
        }
        if(j == 1 && lca.s.size() > 1){
            final_a = final_a - (lca.val.get(1) + lca.val.get(2));
            if(lca.children != null && lca.children.size() > 0){
                final_a = final_a - (lca.children.get(2).count + lca.children.get(3).count);
            }
        }

        else if( j == 2 && lca.s.size() > 2){
            final_a = final_a - (lca.val.get(2));
            if(lca.children != null && lca.children.size() > 0){
                final_a = final_a - (lca.children.get(3).count);
            }
        }

        boolean flag = false;
        TreeNode curr = lca;
       

        while(flag == false){

            int k = curr.s.size() - 1;

            while (k >= 0 && s1.compareTo(curr.s.get(k)) <= 0) {
                k--;
            }

            if(k == curr.s.size()-1){
                curr = curr.children.get(k+1);
                final_a = final_a - curr.count;

                int w = curr.s.size() - 1;
                while (w >= 0 && s1.compareTo(curr.s.get(w)) <= 0) {
                    w--;
                }

                for(int a = 0;a<curr.s.size();a++){
                    if(curr.s.get(a).equals(s1)){
                        flag = true;
                    }
                }

                if(w == curr.s.size() - 1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }

                    else{
                        final_a = final_a + 0;
                        
                        }
                    //curr = curr.children.get(w+1);
                    //curr = curr.children.get(w+1);
                }

                else if(w == -1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }

                    else{
                        for(int t = 0;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }
                }

                else if(w == 0){
                    if(flag == true){
                         for(int t = 1;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 1;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    } 
                }

                else if(w == 1){
                    if(flag == true){
                        for(int t = 2;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 2;t<curr.val.size();t++){
                          final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }  
                }
            }


            else if(k == -1){
                curr = curr.children.get(0);
                final_a = final_a - curr.count;
              

                int w = curr.s.size() - 1;
                while (w >= 0 && s1.compareTo(curr.s.get(w)) <= 0) {
                    w--;
                }
                //System.out.println(w+"w");

                for(int a = 0;a<curr.s.size();a++){
                    if(curr.s.get(a).equals(s1)){
                        flag = true;
                    }
                }

                if(w == curr.s.size() - 1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }

                    else{
                        final_a = final_a + 0;
                        
                        }
                    
                }

                else if(w == -1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                              
                            }
                        }
                    }

                    else{
                        for(int t = 0;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                       
                        }
                    //curr = curr.children.get(w+1);
                    }
                }

                else if(w == 0){
                    if(flag == true){
                         for(int t = 1;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 1;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    } 
                }

                else if(w == 1){
                    if(flag == true){
                        for(int t = 2;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 2;t<curr.val.size();t++){
                          final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }  
                }
            }

            else if(k == 0){
                curr = curr.children.get(1);
                final_a = final_a - curr.count;

                int w = curr.s.size() - 1;
                while (w >= 0 && s1.compareTo(curr.s.get(w)) <= 0) {
                    w--;
                }

                for(int a = 0;a<curr.s.size();a++){
                    if(curr.s.get(a).equals(s1)){
                        flag = true;
                    }

                }

                if(w == -1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size()  > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);

                            }
                        }
                    }

                    else{
                        for(int t = 0;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    } 
                }

                else if(w == 0){
                    if(flag == true){
                         for(int t = 1;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);

                            }
                         
                        }
                    }
                    else{
                         for(int t = 1;t<curr.val.size();t++){
                          final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }
                   
                   
                }

                else if(w == 1){
                    if(flag == true){
                        for(int t = 2;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;

                            }

                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 2;t<curr.val.size();t++){
                          final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }  
                }

            }

            else if (k == 1){
                curr = curr.children.get(2);
                final_a = final_a - curr.count;

                int w = curr.s.size() - 1;
                while (w >= 0 && s1.compareTo(curr.s.get(w)) <= 0) {
                    w--;
                }

                for(int a = 0;a<curr.s.size();a++){
                    if(curr.s.get(a).equals(s1)){
                        flag = true;
                    }

                }

                if(w == -1){
                    if(flag == true){
                        for(int t = 0;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
               
                        }
                    }

                    else{
                        for(int t = 0;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);

                    }
                    
                   
                }

                else if(w == 0){
                    if(flag == true){
                         for(int t = 1;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                        }
                    }
                    else{
                        for(int t = 1;t<curr.val.size();t++){
                        final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    } 
                }

                else if(w == 1){
                    if(flag == true){
                        for(int t = 2;t<curr.val.size();t++){
                            if(curr.children != null && curr.children.size() > 0){
                                final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                            }
                            else{
                                final_a = final_a + curr.val.get(t);
                            }
                          
                        }
                    }
                    else{
                        for(int t = 2;t<curr.val.size();t++){
                          final_a = final_a + curr.val.get(t) + curr.children.get(t+1).count;
                        }
                    //curr = curr.children.get(w+1);
                    }  
                }
            }
        }

        boolean flag1 = false;
        TreeNode curr1 = lca;

        while(flag1 == false){
            int d = 0;

            while(d<curr1.s.size() &&  s2.compareTo(curr1.s.get(d)) >= 0 ){
                d++;
            }
           

            if(d == 0){
                curr1 = curr1.children.get(0);
                final_a = final_a - curr1.count;

                int w = 0;

                while(w<curr1.s.size() &&  s2.compareTo(curr1.s.get(w)) >= 0 ){
                    w++;
                }

                for(int a = 0;a<curr1.s.size();a++){
                    if(curr1.s.get(a).equals(s2)){
                        flag1 = true;
                    }
                }

                if(w == 0){
                    final_a = final_a + 0;
                }


                else if(w == 1){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                        }
                        else{
                            final_a = final_a + curr1.val.get(0);
                        }
                    }
                    else{
                        final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                    }
                   
                }

                else if(w == 2){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }

                        else{
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<2;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }

                    }
                   
                }

                else if(w == 3){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }
                        else{
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<3;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }
                    }
                }
            }

            else if(d == 3){
                curr1 = curr1.children.get(3);
                final_a = final_a - curr1.count;

                int w = 0;

                while(w<curr1.s.size() &&  s2.compareTo(curr1.s.get(w)) >= 0 ){
                    w++;
                }

                for(int a = 0;a<curr1.s.size();a++){
                    if(curr1.s.get(a).equals(s2)){
                        flag1 = true;
                    }
                }

                if(w == 0){
                    final_a = final_a + 0;
                }


                else if(w == 1){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                        }
                        else{
                            final_a = final_a + curr1.val.get(0);
                        }
                    }
                    else{
                        final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                    }
                   
                }

                else if(w == 2){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }

                        else{
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<2;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }

                    }
                   
                }

                else if(w == 3){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }
                        else{
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<3;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }
                    }
                }
            }

            else if(d == 2){
                curr1 = curr1.children.get(2);
                final_a = final_a - curr1.count;
                

                int w = 0;

                while(w<curr1.s.size() &&  s2.compareTo(curr1.s.get(w)) >= 0 ){
                    w++;
                }
             

                for(int a = 0;a<curr1.s.size();a++){
                   

                    if(curr1.s.get(a).equals(s2)){
                        flag1 = true;
                      
                    }

                }

                if(w == 0){
                    final_a = final_a + 0;
                }


                else if(w == 1){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                        }
                        else{
                            final_a = final_a + curr1.val.get(0);
                        }
                    }
                    else{
                        final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                    }
                   
                }

                else if(w == 2){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }

                        else{
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<2;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }

                    }
                   
                }

                else if(w == 3){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }
                        else{
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<3;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }
                    }
                }
            }

            else if(d == 1){

                curr1 = curr1.children.get(1);
                final_a = final_a - curr1.count;

                int w = 0;

                while(w<curr1.s.size() &&  s2.compareTo(curr1.s.get(w)) >= 0 ){
                    w++;
                }

                
                for(int a = 0;a<curr1.s.size();a++){
                    if(curr1.s.get(a).equals(s2)){
                        flag1 = true;
                    }
                }

                if(w == 0){
                    final_a = final_a + 0;
                }


                else if(w == 1){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                        }
                        else{
                            final_a = final_a + curr1.val.get(0);
                        }
                    }
                    else{
                        final_a = final_a + curr1.val.get(0) + curr1.children.get(1).count;
                    }
                   
                }

                else if(w == 2){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }

                        else{
                            for(int a = 0;a<2;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<2;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }

                    }
                   
                }

                else if(w == 3){
                    if(flag1 == true){
                        if(curr1.children != null && curr1.children.size() > 0){
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                            }
                        }
                        else{
                            for(int a = 0;a<3;a++){
                                final_a = final_a + curr1.val.get(a);
                            }
                        }
                    }
                    else{
                        for(int a = 0;a<3;a++){
                            final_a = final_a + curr1.val.get(a) + curr1.children.get(a).count;
                        }
                    }
                }
            }
        }
        return final_a;
    }

    public String maxFreq(String s1, String s2){
        // TO be completed by students
        return "";
    }
}



