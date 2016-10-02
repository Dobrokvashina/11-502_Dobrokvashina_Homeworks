package ru.itis.inform;


import java.io.File;

public class Collector {

    public void collectFromTo(String fromFilename1, String fromFilename2, String fromFilename3, String fromFilename4, String Tofilename) {

        File from1 = new File(fromFilename1);
        File from2 = new File(fromFilename2);
        File from3 = new File(fromFilename3);
        File from4 = new File(fromFilename4);
        HumanReaderWriter reader = new HumanReaderWriter();

        LinkedList<Human> current1 = reader.readHumans(from1);
        LinkedList<Human> current2 = reader.readHumans(from2);
        LinkedList<Human> current3 = reader.readHumans(from3);
        LinkedList<Human> current4 = reader.readHumans(from4);

        Iterator<Human> cur1 = current1.iterator();
        Iterator<Human> cur2 = current2.iterator();
        Iterator<Human> cur3 = current3.iterator();
        Iterator<Human> cur4 = current4.iterator();

        LinkedList<Human> reult = new LinkedList<Human>();

        while (cur1.hasNext() || cur2.hasNext() || cur3.hasNext() || cur4.hasNext()) {
            Human min1 = null;
            int which1 = 0;
            if (cur1.hasNext() && cur2.hasNext()) {
                int minAge = Math.max(cur1.peekNext().getAge(), cur2.peekNext().getAge());
                if (cur1.peekNext().getAge() == minAge) {
                    min1 = cur1.peekNext();
                    which1 = 1;
                } else {
                    min1 = cur2.peekNext();
                    which1 = 2;
                }
            } else {
                if (cur1.hasNext()) {
                    min1 = cur1.peekNext();
                    which1 = 1;
                } else {
                    if (cur2.hasNext()) {
                        min1 = cur2.peekNext();
                        which1 = 2;
                    }
                }
            }

            Human min2 = null;
            int which2 = 0;
            if (cur3.hasNext() && cur4.hasNext()) {
                int minAge = Math.max(cur3.peekNext().getAge(), cur4.peekNext().getAge());
                if (cur3.peekNext().getAge() == minAge) {
                    min2 = cur3.peekNext();
                    which2 = 3;
                } else {
                    min2 = cur4.peekNext();
                    which2 = 4;
                }
            } else {
                if (cur3.hasNext()) {
                    min2 = cur3.peekNext();
                    which2 = 3;
                } else {
                    if (cur4.hasNext()) {
                        min2 = cur4.peekNext();
                        which2 = 4;
                    }
                }
            }

            if (min1 != null && min2 != null) {
                int min = Math.max(min1.getAge(), min2.getAge());
                if (min == min1.getAge()) {
                    if (which1 == 1) {
                        reult.addEnd(min1);
                        cur1.next();
                    } else {
                        reult.addEnd(min1);
                        cur2.next();
                    }
                } else {
                    if (which2 == 3) {
                        reult.addEnd(min2);
                        cur3.next();
                    } else {
                        reult.addEnd(min2);
                        cur4.next();
                    }
                }
            } else {
                if (min1 != null) {
                    if (which1 == 1) {
                        reult.addEnd(min1);
                        cur1.next();
                    } else {
                        reult.addEnd(min1);
                        cur2.next();
                    }
                } else {
                    if (which2 == 3) {
                        reult.addEnd(min2);
                        cur3.next();
                    } else {
                        reult.addEnd(min2);
                        cur4.next();
                    }
                }
            }
        }
        reader.writeHumans(Tofilename, reult);
    }
}

