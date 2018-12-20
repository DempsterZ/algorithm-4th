import java.util.Arrays;

/**
 * 贪心算法
 */
public class greedy {

    public static void main(String[] args) {
//        int res = new greedy().findContentChildren(new int[]{1, 2, 3}, new int[]{3});
//        System.out.println(res);

        int[] a = {1,2,3,4};
        int[] b={2,3,4,5,6,7,8};
        int[] ress=merga(a,b);
        for(int i=0;i<ress.length;i++){
            System.out.print(ress[i]+" ");
        }

    }

    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);

        for (int i = g.length - 1; i >= 0; i--) {
            for (int j = s.length - 1; j >= 0; j--) {
                if (s[j] >= g[i]) {
                    res++;
                    s[j] = 0;
                    break;
                }
            }
        }

        return res;
    }

    public static int[] merga(int[] a, int[] b) {
        int i = 0, j = 0;
        int[] res = new int[a.length + b.length];

        for (int index = 0; index < res.length; index++) {
            if (i >= a.length) {
                res[index] = b[j];
                j++;
                continue;
            }
            if (j >= b.length) {
                res[index] = a[i];
                i++;
                continue;
            }
            if(a[i] < b[j]){
                res[index] = a[i];
                i++;
            }else{
                res[index] = b[j];
                j++;
            }
        }

        return res;

    }

}
