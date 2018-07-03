package com.mirahome.hookdemo;

import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.*;

/**
 * Created by xuxiaowu on 2018/6/26.
 */

public class TestHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        // 可以排除非当前包名
        if (loadPackageParam.packageName.equals("com.mlily.mh") ||
                loadPackageParam.packageName.equals("com.sina.weibo") ||
                loadPackageParam.packageName.equals("com.mirahome.hookdemo")) {

            XposedBridge.log("packageName " + loadPackageParam.packageName);
            final XC_MethodHook hook = new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("----------------------------------- hook before ----------------------------------- ");
//                    for (Object arg : param.args) {
////                        XposedBridge.log("param: " + arg.toString());
//                        if (arg.toString().equals("key_hash")) {
//                            XposedBridge.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + arg.toString());
//                        }
//                    }

                    String par1 = param.args[0].toString();
                    if (par1.equals("extra_key_hash")) {
                        param.args[1] = "a2fe2ae512914f62eab60fe86f618958";
                        XposedBridge.log("change: " + param.args[1].toString());
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                param.setResult("hook");
//                    String par = param.args[0].toString();
//                    XposedBridge.log("key: " + par);
//                    if (par.equals("key_hash") || par.equals("FCUK")) {
//                        XposedBridge.log("pre result: " + param.getResult());
//                        param.setResult("a2fe2ae512914f62eab60fe86f618958");
//                        XposedBridge.log("result: " + param.getResult());
//                    }

//                    XposedBridge.log("key: " + par + " value: " + param.getResult());

                    String par1 = param.args[0].toString();
                    String par2 = param.args[1] == null ? "null" : param.args[1].toString();
                    XposedBridge.log("param1: " + par1 + " param2: " + par2);
                    XposedBridge.log("-----------------------------------  hook after ----------------------------------- ");
                }
            };

//        Class clazz = loadPackageParam.classLoader.loadClass("android.content.Intent");
//        Class clazz = XposedHelpers.findClass("android.content.Intent", loadPackageParam.classLoader);
//        XposedHelpers.findAndHookMethod(clazz, "getStringExtra", hook);
//            XposedHelpers.findAndHookMethod("android.content.Intent", loadPackageParam.classLoader, "getStringExtra", String.class, hook);

            XposedHelpers.findAndHookMethod("android.content.Intent", loadPackageParam.classLoader, "putExtra", String.class, String.class, hook);
            XposedBridge.log("hook exec");
        }
    }
}
