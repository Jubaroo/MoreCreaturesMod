package org.requiem.mods.morecreatures.util;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.logging.Logger;

public class Util {
    public static final Logger logger = Logger.getLogger(Util.class.getName());
    private static boolean success = false;
    private static String reason = "";

    public Util() {
    }

    private static void checkSuccess(int type, Class<?> instrumentingClass, CtClass editClass, String declaredMethod, String methodCall) {
        String editType = "Instrument";
        if (type == 1) {
            editType = "Insert";
        } else if (type == 2) {
            editType = "Set Body";
        }

        String errorType = editType.toUpperCase();
        Logger classLogger = Logger.getLogger(instrumentingClass.getName());
        if (success) {
            classLogger.info(editType + ": " + editClass.getSimpleName() + " - " + declaredMethod + " " + (methodCall.length() > 0 ? "call to " + methodCall + " " : "") + "successful." + reason);
        } else {
            classLogger.severe("[" + errorType + " ERROR] from " + instrumentingClass.getSimpleName() + "! Could not " + editType + " " + editClass.getName() + " - " + declaredMethod + (methodCall.length() > 0 ? " call to " + methodCall + " " : "") + "!" + reason);
        }

        success = false;
        reason = "";
    }

    public static void instrumentDescribed(Class<?> instrumentingClass, CtClass ctToInstrument, String declaredMethod, String descriptor, final String methodCall, final String replace) {
        try {
            ctToInstrument.getMethod(declaredMethod, descriptor).instrument(new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals(methodCall)) {
                        m.replace(replace);
                        Util.success = true;
                    }

                }
            });
            checkSuccess(0, instrumentingClass, ctToInstrument, declaredMethod, methodCall);
        } catch (NotFoundException | CannotCompileException var7) {
            checkSuccess(0, instrumentingClass, ctToInstrument, declaredMethod, methodCall);
            logger.severe(var7.getMessage());
        }

    }

    public static void insertBeforeDescribed(Class<?> instrumentingClass, CtClass ctToInstrument, String declaredMethod, String descriptor, String insert) {
        try {
            ctToInstrument.getMethod(declaredMethod, descriptor).insertBefore(insert);
            success = true;
            checkSuccess(1, instrumentingClass, ctToInstrument, declaredMethod, "");
        } catch (NotFoundException | CannotCompileException var6) {
            checkSuccess(1, instrumentingClass, ctToInstrument, declaredMethod, "");
            logger.severe(var6.getMessage());
        }
    }

}
