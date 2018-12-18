import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        System.out.println("Hello World");

        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        QuickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }

    }

    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++)     //n-1此扫描，依次向前插入n-1个元素
        {
            int temp = a[i];       //每趟将a[i]插入到前面的排序子序列中
            int j;
            for (j = i - 1; j >= 0 && temp < a[j]; j--) {
                a[j + 1] = a[j];  //将前面较大的元素向后移动
            }
            a[j + 1] = temp;      //temp值到达插入位置
        }
    }

    public static void halfSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int left = 0;
            int right = i - 1;
            int temp = a[i];
            while (left <= right) {             // 利用折半查找插入位置
                int mid = (left + right) / 2;   // 取中点
                if (a[mid] > temp)             // 插入值小于中点值
                    right = mid - 1;          // 向左缩小区间
                else
                    left = mid + 1;           // 向右缩小区间
            }
            // left即为找到的要插入的位置，所以下边的循环将left-(i-1)位置的元素依次向后移动
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            a[left] = temp;    // 将temp插入到left位置
        }
    }

    // 希尔排序
    public static void shellsort1(int[] arr) {
        int n = arr.length;
        for (int h = n / 2; h > 0; h /= 2) {
            // 内部是一个插入排序
            for (int i = 0; i < n; i += h) {
                int e = arr[i], j = i;
                for (; j > 0; j -= h) {
                    if (e < arr[j - h]) {
                        arr[j] = arr[j - h];
                    } else {
                        break;
                    }
                }
                arr[j] = e;
            }
        }
    }


    // 希尔排序2
    public static void shellsort2(int[] arr) {
        int n = arr.length;
        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {

                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                int e = arr[i];
                int j = i;
                for (; j >= h && e < arr[j - h]; j -= h)
                    arr[j] = arr[j - h];
                arr[j] = e;
            }

            h /= 3;
        }
    }

    // 归并排序
    public static void mergeSort(int[] arr) {
        __MergeSort(arr, 0, arr.length - 1);
    }

    private static void __MergeSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int mid = (l + r) / 2;
        __MergeSort(arr, l, mid);
        __MergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {  // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {  // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {  // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    // 快速排序
    public static void QuickSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int p = partition(arr, l, r);
        QuickSort(arr, l, p - 1);
        QuickSort(arr, p + 1, r);
    }

    // 将数组通过p分割成两部分
    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    public static int partition(int[] arr, int l, int r) {
//        swap(arr, l, (int) (Math.random() % (r - l + 1)) + l);  // 加入这一行变成随机快速排序

        int v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}