import java.util.ArrayList;
import java.util.List;

public class SHA256 {

    String msg;

    public SHA256(String msg) {
        this.msg = msg;
    }

    // rotate 10101 to right => 11010
    public static String rotateRight(String word){
        StringBuilder buildingWord = new StringBuilder(word);
        buildingWord.insert(0,buildingWord.substring(word.length()-1));
        buildingWord.deleteCharAt(buildingWord.length()-1);
        return buildingWord.toString();
    }

    // rotate 10101 to left => 01011
    public static String rotateLeft(String word){
        StringBuilder buildingWord = new StringBuilder(word);
        buildingWord.append(buildingWord.substring(0,1));
        buildingWord.delete(0,1);
        return buildingWord.toString();
    }

    // shift 10101 to right => 01010
    public static String shiftRight(String word){
        StringBuilder building = new StringBuilder(word);
        building.insert(0,"0");
        building.deleteCharAt(building.length()-1);
        return building.toString();
    }

    // shift 10101 to left => 01010
    public static String shiftLeft(String word){
        StringBuilder building = new StringBuilder(word);
        building.append("0");
        building.delete(0,1);
        return building.toString();
    }

    // rotateLeft x number of times
    public static String rotateLeft(String word, int rotationNumber){
        String results = word;
        for(int i = 0 ; i < rotationNumber ; i++){
            results = rotateLeft(results);
        }
        return results;
    }

    // rotateRight x number of times
    public static String rotateRight(String word, int rotationNumber){
        String results = word;
        for(int i = 0 ; i < rotationNumber ; i++){
            results = rotateRight(results);
        }
        return results;
    }

    // shift right x number of times
    public static String shiftRight(String word, int shiftCount){
        String results = word;
        for(int i = 0 ; i < shiftCount ; i++){
            results = shiftRight(results);
        }
        return results;
    }

    // shift left x number of times
    public static String shiftLeft(String word, int shiftCount){
        String results = word;
        for(int i = 0 ; i < shiftCount ; i++){
            results = shiftLeft(results);
        }
        return results;
    }

    // xor operation in binary
    // example
    // 1001
    // 0001
    // ----
    // 1000
    public static String xOr(String wordOne, String wordTwo){
        StringBuilder results = new StringBuilder();
        for(int i = 0 ; i < wordOne.length() ; i ++){
            if (wordOne.charAt(i) == wordTwo.charAt(i)) {
                results.append("0");
            } else {
                results.append("1");
            }
        }
        return results.toString();
    }

    // and operation in binary
    // example
    // 1100
    // 1010
    // ----
    // 1110
    public static String and(String wordOne, String wordTwo){
        StringBuilder building = new StringBuilder();

        for(int i = 0 ; i < wordOne.length() ; i++){
            if(wordOne.charAt(i) == '1' && wordTwo.charAt(i) == '1'){
                building.append('1');
            }else{
                building.append('0');
            }
        }

        return building.toString();
    }

    // not operation in binary
    // example
    // 1011
    // ----
    // 0100
    public static String not(String word){
        StringBuilder building = new StringBuilder();

        for(int i =0 ; i < word.length() ; i++){
            if(word.charAt(i) == '0'){
                building.append('1');
            }else{
                building.append('0');
            }
        }

        return building.toString();
    }

    public static String wordAddition(String wordOne, String wordTwo){
        StringBuilder building = new StringBuilder();
        int rest = 0;
        for(int i = 31 ; i >=0 ; i--){
            if(wordOne.charAt(i) == '0' && wordTwo.charAt(i) == '0'){
                if ((rest == 0)) {
                    building.insert(0,"0");
                } else {
                    building.insert(0,"1");
                }
                rest = 0;
            }else

            if(wordOne.charAt(i) == '1' && wordTwo.charAt(i) == '1'){
                if ((rest == 0)) {
                    building.insert(0,"0");
                } else {
                    building.insert(0,"1");
                }
                rest = 1;
            }else

            if((wordOne.charAt(i) == '1' && wordTwo.charAt(i) == '0') || (wordOne.charAt(i) == '0' && wordTwo.charAt(i) == '1')){
                if ((rest == 1)) {
                    building.insert(0,"0");
                    rest = 1;
                } else {
                    building.insert(0,"1");
                    rest = 0;
                }
            }

        }

        return  building.toString();
    }

    public static String calculateWord(List<String> words, int wordLocation){
        String s0 = xOr(xOr(rotateRight(words.get(wordLocation-15),7),rotateRight(words.get(wordLocation-15),18)),shiftRight(words.get(wordLocation-15),3));
        String s1 = xOr(xOr(rotateRight(words.get(wordLocation-2),17),rotateRight(words.get(wordLocation-2),19)),shiftRight(words.get(wordLocation-2),10));
        return  wordAddition(wordAddition(words.get(wordLocation-16), words.get(wordLocation-7)), wordAddition(s0, s1));
    }

    public static String completeWord(String word){
        StringBuilder building = new StringBuilder(word);
        while(building.length() < 32) building.insert(0,"0");
        return building.toString();
    }

    public static String convertToHex(String hash){
        int bytesFound = hash.length() / 4;
        StringBuilder results = new StringBuilder();
        for(int i = 0 ; i < bytesFound ; i++){
            String oct = hash.substring((i*4),(i*4)+4);
            if (oct.equals("0000")){
                results.append("0");
            }
            if (oct.equals("0001")){
                results.append("1");
            }
            if (oct.equals("0010")){
                results.append("2");
            }
            if (oct.equals("0011")){
                results.append("3");
            }
            if (oct.equals("0100")){
                results.append("4");
            }
            if (oct.equals("0101")){
                results.append("5");
            }
            if (oct.equals("0110")){
                results.append("6");
            }
            if (oct.equals("0111")){
                results.append("7");
            }
            if (oct.equals("1000")){
                results.append("8");
            }
            if (oct.equals("1001")){
                results.append("9");
            }
            if (oct.equals("1010")){
                results.append("a");
            }
            if (oct.equals("1011")){
                results.append("b");
            }
            if (oct.equals("1100")){
                results.append("c");
            }
            if (oct.equals("1101")){
                results.append("d");
            }
            if (oct.equals("1110")){
                results.append("e");
            }
            if (oct.equals("1111")){
                results.append("f");
            }
        }

        return results.toString();
    }


    public static void main(String[] args) {
        String msg = "hello world";
        System.out.println(msg);

        StringBuilder preparationData = new StringBuilder();

        for(byte bit:msg.getBytes()){
            StringBuilder bitValue = new StringBuilder(Integer.toBinaryString(bit));
            while(bitValue.length() < 8) bitValue.insert(0,"0");
            preparationData.append(bitValue.toString());
        }

        int msgLengthInBinary = preparationData.length();

        preparationData.append("1");

        int chunkCount = preparationData.length() / 512;

        while(preparationData.length() != (chunkCount + 1) * (512) - 64) preparationData.append("0");

        StringBuilder msgLBP = new StringBuilder(Integer.toBinaryString(msgLengthInBinary));

        while(msgLBP.length() < 64) msgLBP.insert(0,"0");

        preparationData.append(msgLBP);

        // initial hash values
        int[] hashValues = {
                                0x6a09e667,
                                0xbb67ae85,
                                0x3c6ef372,
                                0xa54ff53a,
                                0x510e527f,
                                0x9b05688c,
                                0x1f83d9ab,
                                0x5be0cd19
                            };


        // initial round constants
        int[] roundConstants = {
                                    0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
                                    0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
                                    0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
                                    0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
                                    0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
                                    0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
                                    0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
                                    0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
                                };



        List<String> words = new ArrayList<>();

        for(int i = 0 ; i < 512 / 32 ; i++ ){
            words.add(preparationData.substring((i*32),(i*32)+32));
        }

        while(words.size()<64){
            words.add("0".repeat(32));
        }


        for(int i = 16; i < 64 ; i++){
            words.remove(i);
            words.add(i, calculateWord(words, i));
        }

        String a = completeWord(Integer.toBinaryString(hashValues[0]));
        String b = completeWord(Integer.toBinaryString(hashValues[1]));
        String c = completeWord(Integer.toBinaryString(hashValues[2]));
        String d = completeWord(Integer.toBinaryString(hashValues[3]));
        String e = completeWord(Integer.toBinaryString(hashValues[4]));
        String f = completeWord(Integer.toBinaryString(hashValues[5]));
        String g = completeWord(Integer.toBinaryString(hashValues[6]));
        String h = completeWord(Integer.toBinaryString(hashValues[7]));


        for(int chunks = 0 ; chunks <= chunkCount ; chunks++) {
            for (int i = 0; i < 64; i++) {
                String s1 = xOr(xOr(rotateRight(e, 6), rotateRight(e, 11)), rotateRight(e, 25));
                String ch = xOr(and(e, f), and(not(e), g));
                String temp1 = wordAddition(wordAddition(wordAddition(h, s1), wordAddition(ch, completeWord(Integer.toBinaryString(roundConstants[i])))), words.get(i));
                String s0 = xOr(xOr(rotateRight(a, 2), rotateRight(a, 13)), rotateRight(a, 22));
                String maj = xOr(xOr(and(a, b), and(a, c)), and(b, c));
                String temp2 = wordAddition(s0, maj);

                h = g;
                g = f;
                f = e;
                e = wordAddition(d, temp1);
                d = c;
                c = b;
                b = a;
                a = wordAddition(temp1, temp2);
            }
        }


        String h0 = wordAddition(completeWord(Integer.toBinaryString(hashValues[0])), a);
        String h1 = wordAddition(completeWord(Integer.toBinaryString(hashValues[1])), b);
        String h2 = wordAddition(completeWord(Integer.toBinaryString(hashValues[2])), c);
        String h3 = wordAddition(completeWord(Integer.toBinaryString(hashValues[3])), d);
        String h4 = wordAddition(completeWord(Integer.toBinaryString(hashValues[4])), e);
        String h5 = wordAddition(completeWord(Integer.toBinaryString(hashValues[5])), f);
        String h6 = wordAddition(completeWord(Integer.toBinaryString(hashValues[6])), g);
        String h7 = wordAddition(completeWord(Integer.toBinaryString(hashValues[7])), h);



        String finalHash = h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7;

        System.out.println(convertToHex(finalHash));



    }
}
