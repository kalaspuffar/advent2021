import java.math.BigInteger;

public class MathPair {
    Object left;
    Object right;
    int depth = 0;

    boolean visited = false;

    public MathPair() {}

    public MathPair(String s) {
        int begin = 0;
        int end = s.length();
        if(s.startsWith("[")) begin++;
        if(s.endsWith("]")) end--;

        String terms = splitTerms(s.substring(begin, end));

        String leftStr = terms.split("\\|")[0];
        if (leftStr.contains(",")) {
            this.left = new MathPair(leftStr);
        } else {
            this.left = Integer.parseInt(leftStr);
        }

        String rightStr = terms.split("\\|")[1];
        if (rightStr.contains(",")) {
            this.right = new MathPair(rightStr);
        } else {
            this.right = Integer.parseInt(rightStr);
        }
    }

    public MathPair(MathPair left, MathPair right) {
        this.left = left;
        this.right = right;
    }

    private String splitTerms(String s) {
        if(s.startsWith("[")) {
            int split;
            String[] myChars = s.split("");
            int balance = 1;
            for (split = 1; split < s.length(); split++) {
                if ("[".equals(myChars[split])) balance++;
                if ("]".equals(myChars[split])) balance--;
                if (balance == 0) break;
            }
            return s.substring(0, split + 1) + "|" + s.substring(split + 2);
        } else {
            return s.replaceFirst(",", "|");
        }
    }

    public void setDepth(int val) {
        depth = val;
        if(left instanceof MathPair) {
            ((MathPair) left).setDepth(val + 1);
        }
        if(right instanceof MathPair) {
            ((MathPair) right).setDepth(val + 1);
        }
        this.visited = false;
    }

    public MathPair explode() {
        MathPair mp = null;
        if (left instanceof MathPair) {
            mp = ((MathPair) left).explode();
        }
        if(mp == null && right instanceof MathPair) {
            mp = ((MathPair) right).explode();
        }

        if (
            depth > 4 &&
            left instanceof Integer &&
            right instanceof Integer
        ) {
            return this;
        }

        if (mp != null) {
            if (this.left.equals(mp)) {
                this.left = 0;
                this.visited = true;
                if (right instanceof MathPair) {
                    if(((MathPair) right).visited) {
                        this.visited = true;
                        return mp;
                    }
                    return ((MathPair) right).setRight(mp);
                } else {
                    this.right = (Integer) this.right + (Integer) mp.right;
                    mp.right = -1;
                    return mp;
                }
            } else if (this.right.equals(mp)) {
                this.right = 0;
                this.visited = true;
                if(left instanceof MathPair) {
                    if(((MathPair) left).visited) {
                        this.visited = true;
                        return mp;
                    }
                    return ((MathPair) left).setLeft(mp);
                } else if(this.left instanceof Integer) {
                    this.left = (Integer) this.left + (Integer) mp.left;
                    mp.left = -1;
                    return mp;
                }
            } else {
                if ((Integer)mp.left > 0) {
                    if(left instanceof MathPair) {
                        if(((MathPair) left).visited) {
                            this.visited = true;
                            return mp;
                        }
                        return ((MathPair) left).setLeft(mp);
                    } else {
                        this.left = (Integer) this.left + (Integer) mp.left;
                        mp.left = -1;
                    }
                }
                if ((Integer)mp.right > 0) {
                    if (right instanceof MathPair) {
                        if(((MathPair) right).visited) {
                            this.visited = true;
                            return mp;
                        }
                        return ((MathPair) right).setRight(mp);
                    } else {
                        this.right = (Integer) this.right + (Integer) mp.right;
                        mp.right = -1;
                        return mp;
                    }
                }
            }
        }
        return mp;
    }

    private MathPair setLeft(MathPair mp) {
        if(this.right instanceof MathPair) {
            return ((MathPair) this.right).setLeft(mp);
        } else {
            this.right = (Integer) this.right + (Integer) mp.left;
            mp.left = -1;
            return mp;
        }
    }

    private MathPair setRight(MathPair mp) {
        if(this.left instanceof MathPair) {
            return ((MathPair) this.left).setRight(mp);
        } else {
            this.left = (Integer) this.left + (Integer) mp.right;
            mp.right = -1;
            return mp;
        }
    }

    public boolean split() {
        if (left instanceof MathPair) {
            if(((MathPair) left).split()) {
                return true;
            }
        } else {
            Integer val = (Integer) left;
            if (val > 9) {
                MathPair mp = new MathPair();
                mp.setLeft(Math.floorDiv(val, 2));
                mp.setRight(val - Math.floorDiv(val, 2));
                left = mp;
                return true;
            }
        }
        if (right instanceof MathPair) {
            if(((MathPair) right).split()) {
                return true;
            }
        } else {
            Integer val = (Integer) right;
            if (val > 9) {
                MathPair mp = new MathPair();
                mp.setLeft(Math.floorDiv(val, 2));
                mp.setRight(val - Math.floorDiv(val, 2));
                right = mp;
                return true;
            }
        }
        return false;
    }

    public void setLeft(Integer val) {
        this.left = val;
    }
    public void setRight(Integer val) {
        this.right = val;
    }

    @Override
    public String toString() {
        String deep = depth > 4 ? "*" : "";
        return "[" + left + "," + right + "]" + deep;
    }

    public BigInteger getMagnitude() {
        BigInteger bi = BigInteger.ZERO;
        if(left instanceof MathPair) {
            BigInteger rightBi = ((MathPair) left).getMagnitude();
            bi = bi.add(rightBi.multiply(new BigInteger("3")));
        } else {
            BigInteger leftBi = new BigInteger("" + left);
            bi = bi.add(leftBi.multiply(new BigInteger("3")));
        }
        if(right instanceof MathPair) {
            BigInteger rightBi = ((MathPair) right).getMagnitude();
            bi = bi.add(rightBi.multiply(new BigInteger("2")));
        } else {
            BigInteger rightBi = new BigInteger("" + right);
            bi = bi.add(rightBi.multiply(new BigInteger("2")));
        }
        return bi;
    }

    public int maxDepth() {
        int currentDepth = this.depth;
        if (left instanceof MathPair) {
            int leftDepth = ((MathPair) left).maxDepth();
            if (leftDepth > currentDepth) {
                currentDepth = leftDepth;
            }
        }
        if (right instanceof MathPair) {
            int rightDepth = ((MathPair) right).maxDepth();
            if (rightDepth > currentDepth) {
                currentDepth = rightDepth;
            }
        }
        return currentDepth;
    }

    public int maxNum() {
        int currentNum = 0;
        if (left instanceof MathPair) {
            int leftDepth = ((MathPair) left).maxNum();
            if (leftDepth > currentNum) {
                currentNum = leftDepth;
            }
        } else {
            if ((Integer)left > currentNum) {
                currentNum = (Integer)left;
            }
        }
        if (right instanceof MathPair) {
            int rightDepth = ((MathPair) right).maxNum();
            if (rightDepth > currentNum) {
                currentNum = rightDepth;
            }
        } else {
            if ((Integer)right > currentNum) {
                currentNum = (Integer)right;
            }
        }
        return currentNum;
    }
}
