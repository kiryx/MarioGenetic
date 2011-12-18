///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package mariogenetic;
//
//import java.util.HashMap;
//
///**
// *
// * @author alice
// */
//public class Localization {
//
//
//    HashMap<Lang, HashMap<Label, String> > locals = new HashMap<Lang, HashMap<Label, String> >();
//    Lang language = null;
//    public static Localization singleton = null;
//    public static Localization getInstance()
//    {
//        if(singleton == null)
//            singleton = new Localization(Lang.EN);
//        return singleton;
//    }
//    private Localization(Lang l)
//    {
//        language = l;
//    }
//    enum Lang{
//        PL,EN
//    }
//
//    enum Label{
//
//    }
//    public void setLanguage(Lang l)
//    {
//        language = l;
//    }
//    public String get_string(Label l)
//    {
//        return locals.get(language).get(l);
//    }
//}
