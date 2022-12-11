package hot100;

public class solution4 {
    //暴力
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {

                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

    //
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            //什么时候遍历a
            //遍历a当前位置小于m且(b当前位置大于n（b遍历完了）等于或者a当前位置元素小于b（大部分的情况）)
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart];
                aStart++;
            } else {
                right = B[bStart];
                bStart++;
            }
        }

        //判断奇偶
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    //我们比较两个数组的第 k/2 个数字，如果 k 是奇数，向下取整。也就是比较第 333 个数字，
    // 上边数组中的 4 和下边数组中的 3，如果哪个小，就表明该数组的前 k/2 个数字都不是第 k 小数字，
    // 所以可以排除。也就是 1，2，3 这三个数字不可能是第 7 小的数字，我们可以把它排除掉。
    // 将 1349 和 45678910 两个数组作为新的数组进行比较。
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //中间两个值，为两个数组合并后中间的两个位置，要找的第几个的位置
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
                getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1,
                       int[] nums2, int start2, int end2, int k) {
        //数组一和数组二的长度
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2,
                nums1, start1, end1, k);

        //当数组一的长度为0，返回数组二第k（偏左或偏右）个元素
        if (len1 == 0) return nums2[start2 + k - 1];
        //偶数的时候且，两个元素
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        //代表遍历的当前位置
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        //多次递归
        //先比两个数组k/2位置（如何长度小于这个位置直接取最后一个）的数，
        //小的话 ，对于这个数组来说，包括这个小的数字以左的都不要，更新k,k=k-左k/2的那些数字的数量
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1,
                    nums2, j + 1, end2, k - (j + 1 - start2));
        } else {
            return getKth(nums1, i + 1, end1,
                    nums2, start2, end2, k - (i + 1 - start1));
        }
    }

    public double findMedianSortedArrays4(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // 保证 m <= n
        if (m > n) {
            return findMedianSortedArrays4(B, A);
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            //i动，j跟着动
            int j = (m + n + 1) / 2 - i;
            //偏右位置不等于0且i!=m(偏左的位置不等于m)且b>a
            if (j != 0 && i != m && B[j - 1] > A[i]) {
                // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && A[i - 1] > B[j]) {
                // i 需要减小
                iMax = i - 1;
            } else {
                // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                //特殊条件
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    //两个数组左半边最大的是
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }

                // 奇数的话不需要考虑右半部分
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                //找到两个数组右半边最小的
                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                //如果是偶数的话返回结果
                return (maxLeft + minRight) / 2.0;

            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        solution4 solution4=new solution4();
        solution4.findMedianSortedArrays4(new int[]{1,3,5},new int[]{2,4,6});
    }
}
