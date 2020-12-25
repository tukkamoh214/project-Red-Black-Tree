package com.jetbrains;

import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;

class BSTNODE
{
    int data;
    BSTNODE left;
    BSTNODE right;
    BSTNODE parent;
    int color;
    BSTNODE()
    {
        left=null;
        right=null;
        parent=null;

    }
    BSTNODE(int key,BSTNODE lef,BSTNODE righ,BSTNODE p)
    {
        data=key;
        left=lef;
        right=righ;
        parent=p;
    }
}


public class rbt {

   public static BSTNODE root;
        rbt()
        {
            root=null;
        }
        public void BSTinsert(BSTNODE node)
        {
            BSTNODE cur=root,prev=null;
            while(cur!=null)
            {
                prev=cur;
                if(node.data>cur.data)
                    cur=cur.right;
                else
                    cur=cur.left;
            }
            if(root==null)
                root=node;
            else if(node.data>prev.data)
                prev.right=node;
            else
                prev.left=node;

            node.parent=prev;
        }
        public void leftrotate(BSTNODE x)
        {
            BSTNODE y=x.right,r;
            r=y.left;
            x.right=r;
            if(x.right!=null)
                x.right.parent=x;
            y.parent=x.parent;
            if(x.parent==null)
                root=y;
            else if(x==x.parent.left)
                x.parent.left=y;
            else
                x.parent.right=y;
            y.left=x;
            x.parent=y;
        }
        public void rightrotate(BSTNODE y)
        {
            BSTNODE x=y.left,l;
            l=x.right;
            y.left=l;
            if(y.left!=null)
                y.left.parent=y;
            x.parent=y.parent;

            if(y.parent==null)
                root=x;
            else if(y==y.parent.left)
                y.parent.left=x;
            else
                y.parent.right=x;
            x.right=y;
            y.parent=x;
        }
        public void insert(int d)
        {
            BSTNODE x=new BSTNODE(d,null,null,null);
            BSTinsert(x);
            x.color=0;
            while(x!=root&&x.parent.color==0&&x.color==0)
            {
                BSTNODE p=x.parent,Grand=x.parent.parent;
                if(x.parent==x.parent.parent.left)
                {

                    BSTNODE y;
                    y = x.parent.parent.right;
                    if(y!=null&&y.color==0)
                    {
                        y.color=1;
                        x.parent.color=1;
                        x.parent.parent.color=0;
                        x=x.parent.parent;
                    }
                    else  ///y.color is black
                    {
                        if(x==x.parent.right)
                        {
                            x=x.parent;
                            leftrotate(p);
                        }
                        x.parent.color=1;
                        x.parent.parent.color=0;
                        rightrotate(Grand);
                        x=x.parent;
                    }
                }
                else
                {
                    BSTNODE y;
                    y = x.parent.parent.left;
                    if(y!=null&&y.color==0)
                    {
                        y.color=1;
                        x.parent.color=1;
                        x.parent.parent.color=0;
                        x=x.parent.parent;
                    }
                    else  ///y.color is black
                    {
                        if(x==x.parent.left)
                        {
                            x=x.parent;
                            rightrotate(p);
                        }
                        x.parent.color=1;
                        x.parent.parent.color=0;
                        leftrotate(Grand);
                        x=x.parent;
                    }
                }
            }
            if(root.color==0)
            {
                root.color=1;
            }
        }
        BSTNODE Find(int d)
        {
            BSTNODE cur=root;
            while(cur.data!=d)
            {
                if(d>cur.data)
                    cur=cur.right;
                else
                    cur=cur.left;
            }
            return cur;
        }
        BSTNODE  successor(BSTNODE  s)
        {
            BSTNODE l=s.right;
            while(l.left!=null)
            {
                l=l.left;
            }
            return l;
        }
        private void swap(int key, int key1) {
            int t=key;
            key=key1;
            key1=t;

        }
        void DeleteRBT(int d)
        {
            BSTNODE cur=Find(d);///making it leaf node
            while(cur.right!=null||cur.left!=null)
            {
                if(cur.right==null&&cur.left!=null)
                {
                    swap(cur.data,cur.left.data);
                    cur=cur.left;
                }
                else if(cur.right!=null&&cur.left==null)
                {
                    swap(cur.data,cur.right.data);
                    cur=cur.right;
                }
                else
                {
                    BSTNODE k=successor(cur);
                    swap(k.data,cur.data);
                    cur=k;
                }
            }///case 1 red node
            if(cur.color==0)
            {
                cur.parent.right=cur.right;
                cur.parent.left=cur.left;
                cur=null;
                return;
            }///case 2 black node
            BSTNODE del=cur;
            cur.color=2;///double black
            BSTNODE y;
            do
            {
                if(cur==cur.parent.left)
                    y=cur.parent.right;
                else
                    y=cur.parent.left;
                if(y==null||y.color==1)
                {
                    if(y==null)
                    {
                        cur.color=1;
                        if(cur.parent.color==1)
                        {
                            cur.parent.color=2;
                            cur=cur.parent;///double black
                        }
                        else
                            cur.parent.color=1;
                    }
                    else if((y.left==null&&y.right==null)||(y.right.color==1&&y.left.color==1))
                    {
                        cur.color=1;
                        if(cur.parent.color==1)
                        {
                            cur.parent.color=2;
                            cur=cur.parent;///double black
                        }
                        else
                            cur.parent.color=1;
                        y.color=0;

                    }
                    else if((cur==cur.parent.left)&&((y.right==null&&y.left.color==0)||(y.right.color==1&&y.left.color==0)))
                    {
                        swap(y.left.color,y.color);
                        rightrotate(y);
                    }
                    else if((cur==cur.parent.left)&&((y.left==null&&y.right.color==0)||(y.left.color==1&&y.right.color==0)))
                    {
                        swap(y.parent.color,y.color);
                        leftrotate(y.parent);
                        cur.color=1;
                        y.right.color=1;

                    }
                    else if((cur==cur.parent.right)&&((y.right==null&&y.left.color==0)||(y.right.color==1&&y.left.color==0)))
                    {
                        swap(y.parent.color,y.color);
                        rightrotate(y.parent);
                        cur.color=1;
                        y.left.color=1;
                    }
                    else if((cur==cur.parent.right)&&((y.left==null&&y.right.color==0)||(y.left.color==1&&y.right.color==0)))
                    {
                        swap(y.right.color,y.color);
                        leftrotate(y);
                    }else if((cur==cur.parent.left)&&(y.right.color==0&&y.left.color==0)){
                        swap(y.left.color,y.color);
                        rightrotate(y);
                    }else if((cur==cur.parent.right)&&(y.right.color==0&&y.left.color==0)){
                        swap(y.right.color,y.color);
                        leftrotate(y);
                    }
                }
                else if(y!=null&&y.color==0)
                {
                    swap(cur.parent.color,y.color);
                    if(cur==cur.parent.left)
                        leftrotate(cur.parent);
                    else
                        rightrotate(cur.parent);
                }
                if(cur==root)cur.color=1;
            }
            while(cur.color==2);
            if(del==del.parent.left)
                del.parent.left=null;
            else
                del.parent.right=null;
            del=null;
        }
        public void inorder(BSTNODE  p)
        {
            if(p!=null)
            {
                inorder(p.left);
                System.out.print(p.data);
                System.out.print(" ");
                if(p.color==1)
                    System.out.print("BLACK\n");
                else if(p.color==0)
                    System.out.print("RED\n");
                inorder(p.right);
            }
        }
        void ClearAll(BSTNODE  p){
            if(p!=null)
            {
                ClearAll(p.left);
                ClearAll(p.right);
                p=null;
            }

        }
        void Clear(){
            ClearAll(root);
        }
        void print()
        {
            inorder(root);
        }
    private static int counterr;
    private static DelegateForest<BSTNODE, Integer> my;

    static Forest<BSTNODE, Integer> buildGraph() {
        my = new DelegateForest<>();
        //my.addEdge( id,parent , node) my.addVertex(node)
        counterr=0;
        buildmygraph(root);
        return my;
    }

    static void buildmygraph(BSTNODE nodde) {
        my.addVertex(nodde);
        if(nodde.right!= null ){
            my.addEdge(counterr,nodde,nodde.right);
            counterr+=1;
            buildmygraph(nodde.right);
        }
        if(nodde.left!= null) {

            my.addEdge(counterr,nodde,nodde.left);
            counterr+=1;
            buildmygraph(nodde.left);
        }

    }
    };









