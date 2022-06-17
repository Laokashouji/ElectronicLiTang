package edu.zip;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class MyHuffmanTree {

    @Test
    public void testZip(){
        String srcFile="E:\\ElectronicLiTang\\src\\data\\file\\1655339714871.jpg";
        String zipFile="E:\\ElectronicLiTang\\src\\data\\file\\1655339714871.zip";
        zipFile(srcFile, zipFile);
        System.out.println("文件压缩成功");
    }
    @Test
    public void testUnZip(){
        String srcFile="E:\\ElectronicLiTang\\src\\data\\file\\1655339714871.zip";
        String zipFile="E:\\ElectronicLiTang\\src\\data\\file\\1.jpg";
        unZipFile(srcFile, zipFile);
        System.out.println("文件解压成功");
    }

    public static void main(String[] args) {

        String srcFile="d://codeTemp.jpg";
        String zipFile="d://codeTemp.zip";
        zipFile(srcFile, zipFile);
        System.out.println("文件压缩成功");

//        String zipFile = "d://codeTemp.zip";
//        String unZipFile = "d://codeTemp1.jpg";
//        unZipFile(zipFile, unZipFile);
//        System.out.println("文件解压成功");

    }

    public static void unZipFile(String zipFile, String dstFile) {
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] srcbytes = huffmanUnZip(huffmanCodes, huffmanBytes);

            os = new FileOutputStream(dstFile);
            os.write(srcbytes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }


    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            is = new FileInputStream(srcFile);
            byte[] bytesFile = new byte[is.available()];
            is.read(bytesFile);
            byte[] bytes = huffmanZip(bytesFile);

            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(bytes);

            oos.writeObject(huffmanMap);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private static byte[] huffmanUnZip(Map<Byte, String> haffmanMap, byte[] srcbytes) {

        String s = bytesToBitsting(srcbytes);

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> maps : haffmanMap.entrySet()) {
            map.put(maps.getValue(), maps.getKey());
        }
        int count = 1;
        boolean flage = true;
        Byte b = null;
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < s.length(); ) {
            String temp = null;
            while (flage) {
                temp = s.substring(i, i + count);
                b = map.get(temp);
                if (b != null) {
                    i += count;
                    count = 1;
                    flage = false;
                } else {
                    count++;
                }
            }
            list.add(b);
            flage = true;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private static String bytesToBitsting(byte[] bytes) {
        boolean flag = true;
        String str = "";
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i];
            if (i != bytes.length - 1) {
                b=b|256;
            } else {
                flag = false;
            }
            String s = Integer.toBinaryString(b);
            if (flag) {
                String substring = s.substring(s.length() - 8);
                str += substring;
            } else {
                str += s;
            }
        }
        return str;
    }

    private static byte[] huffmanZip(byte[] contentByte) {
        List<Node> nodes = getNodes(contentByte);
        Node nodeRoot = creatHuffmanTree(nodes);
        creatHuffmanCode(nodeRoot);
        byte[] zip = zip(contentByte, huffmanMap);
        return zip;
    }


    private static byte[] zip(byte[] contentByte, Map<Byte, String> huffmanMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte bytes : contentByte) {
            stringBuilder.append(huffmanMap.get(bytes));
        }

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {

            if (i + 8 > stringBuilder.length()) {
                huffmanByte[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanByte[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            index++;
        }

        return huffmanByte;
    }

    static Map<Byte, String> huffmanMap = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static void creatHuffmanCode(Node node) {
        creatHuffmanCode(node, "", stringBuilder);
    }

    private static void creatHuffmanCode(Node node, String track, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(track);
        if (node != null) {
            if (node.data == null) {
                creatHuffmanCode(node.leftNode, "0", stringBuilder2);
                creatHuffmanCode(node.rightNode, "1", stringBuilder2);
            } else {
                huffmanMap.put(node.data, stringBuilder2.toString());
            }
        }

    }

    private static List<Node> getNodes(byte[] contentByte) {
        List nodes = new ArrayList<Node>();

        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : contentByte) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node creatHuffmanTree(List<Node> nodes) {
        if (nodes == null) {
            return null;
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.leftNode = left;
            parent.rightNode = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("为空，前序遍历失败");
        } else {
            root.preOrder();
        }
    }

}


class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node leftNode;
    Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        if (this == null) {
            return;
        }
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}