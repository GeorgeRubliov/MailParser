//package com.rublov.heorhii.test;
//
///**
// * @author Rublev Heorhii
// */
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import javax.activation.DataHandler;
//import javax.mail.*;
//import javax.mail.search.FlagTerm;
//import java.io.IOException;
//import java.util.*;
//
///**
// * before using a google account you need to torn on Less secure app by \
// * link: https://myaccount.google.com/lesssecureapps
// */
//
//public class GetMail {
//
//    /**
//     *
//     * @param from
//     * @param password
//     */
//    public List<String> getMails(final String from, final String password, String subject){
//
//        /**
//         *
//         */
//        List<String> tempList = new ArrayList<>();
//
//        /**
//         *
//         */
//        Properties props = new Properties();
//        props.put("mail.pop3.host", "imap.gmail.com");
//        props.put("mail.pop3.port", "995");
//        props.put("mail.pop3.starttls.enable", "true");
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
//            protected PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication(from, password);
//            }
//        });
//
//
//        /**
//         * getting mails,
//         */
//        try {
//            Store store = session.getStore("imaps");
//            store.connect(props.getProperty("mail.pop3.host"), from, password);
//            Folder folder = store.getFolder("inbox");
//            folder.open(Folder.READ_ONLY);
//            List<Message> messages = Arrays.asList(folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)));
//            List<Message> filteredMessage = new ArrayList<>();
//            for(Message message: messages){
//                if(message.getSubject().contains(subject)){
//                    filteredMessage.add(message);
//                }
//            }
//            for(int mes = filteredMessage.size()-1; mes>=0; mes--){
//                Message message = filteredMessage.get(mes);
//                tempList.add(readMime((Multipart) message.getContent()));
//            }
//            folder.close();
//            store.close();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("tempList");
//        System.out.println(tempList);
//
//        return tempList;
//    }
//
//    /**
//     * reading Multipart
//     * @param multipart
//     * @return
//     * @throws MessagingException
//     */
//    private String readMime(Multipart multipart) throws MessagingException {
//        String temp = "";
//
//        for (int x = 0; x < multipart.getCount(); x++) {
//            BodyPart bodyPart = multipart.getBodyPart(x);
//            String disposition = bodyPart.getDisposition();
//            if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
//                System.out.println("Mail have some attachment : ");
//
//                DataHandler handler = bodyPart.getDataHandler();
//                System.out.println("file name : " + handler.getName());
//            } else {
//                try {
//                    temp = String.valueOf(bodyPart.getContent());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        /**
//         * cleaning text from HTML code
//         */
//        Document doc = Jsoup.parse(temp);
//            temp = doc.body().text();
//
//        /**
//         * split text on lines and adding information in str massive
//         */
//
//        List<String> markedListText = markedListOffText(temp.split("\r"));
//        for(int mlc=0; mlc<markedListText.size(); mlc++){
//            System.out.println("-------------------"+mlc+"-----------------------");
//            String[] t = markedListText.get(mlc).split(";");
//            for(int i=0; i<t.length;i++){
//                System.out.println(i+".   "+t[i]);
//            }
//        }
//       writeNewXmlFile(markedListText);
//        return temp;
//    }
//
//
//    /**
//     *
//     * @param markedListText
//     */
//    private void writeNewXmlFile(List<String> markedListText) {
//    }
//
//    /**
//     *
//     * @param split
//     * @return ArrayList
//     */
//    private List<String> markedListOffText(String[] split) {
//        List<String> temp = new ArrayList<>();
//        for(int i=0; i<split.length;i++){
//            temp.add(split[i].replaceAll("[\\s]{2,}",";"));
//        }
//        return temp;
//    }
//}
