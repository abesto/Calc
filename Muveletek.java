/**
 * @author Nagy Zoltán
 */

package Calc;

import java.util.HashMap;

interface KetOp {
   Float call(Float a, Float b);
}

interface EgyOp {
   Float call(Float b);
}

public class Muveletek {
    private static Muveletek instance = null;
    private HashMap<String, KetOp> ketOps = new HashMap();
    private HashMap<String, EgyOp> egyOps = new HashMap();
    private String szogMod = "Fok";

    public String toggleSzogMod() {
        if (szogMod.equals("Fok")) szogMod = "Radián";
        else szogMod = "Fok";
        return szogMod;
    }

    protected Muveletek() {
        ketOps.put("+", new KetOp() {
            public Float call(final Float a, final Float b) {
                return a + b;
            }
        });
        ketOps.put("-", new KetOp() {
            public Float call(final Float a, final Float b) {
                return a - b;
            }
        });
        ketOps.put("/", new KetOp() {
            public Float call(final Float a, final Float b) {
                return a / b;
            }
        });
        ketOps.put("*", new KetOp() {
            public Float call(final Float a, final Float b) {
                return a * b;
            }
        });
        ketOps.put("pow", new KetOp() {
            public Float call(final Float a, final Float b) {
                return new Float(Math.pow(a, b));
            }
        });
        ketOps.put("gyok", new KetOp() {
            public Float call(final Float a, final Float b) {
                return new Float(Math.pow(a, 1/b));
            }
        });

        egyOps.put("+/-", new EgyOp() {
            public Float call(final Float n) {
                return new Float(-n);
            }
        });
        egyOps.put("^2", new EgyOp() {
            public Float call(final Float n) {
                return new Float(Math.pow(n, 2));
            }
        });
        egyOps.put("sqrt", new EgyOp() {
            public Float call(final Float n) {
                return new Float(Math.sqrt(n));
            }
        });
        egyOps.put("sin", new EgyOp() {
            public Float call(final Float n) {
                return new Float(Math.sin(to_radian(n)));
            }
        });
        egyOps.put("asin", new EgyOp() {
            public Float call(final Float n) {
                return to_fok(new Float(Math.asin(n)));
            }
        });
        egyOps.put("cos", new EgyOp() {
            public Float call(final Float n) {
                return new Float(Math.cos(to_radian(n)));
            }
        });
        egyOps.put("acos", new EgyOp() {
            public Float call(final Float n) {
                return to_fok(new Float(Math.acos(n)));
            }
        });
        egyOps.put("tan", new EgyOp() {
            public Float call(final Float n) {
                return new Float(Math.tan(to_radian(n)));
            }
        });
        egyOps.put("atan", new EgyOp() {
            public Float call(final Float n) {
                return to_fok(new Float(Math.atan(n)));
            }
        });
    }

    private Float to_radian(Float n) {
        if (szogMod.equals("Fok"))
            return new Float(Math.toRadians(n));
        return n;
    }

    private Float to_fok(Float n) {
        if (szogMod.equals("Fok"))
            return new Float(Math.toDegrees(n));
        return n;
    }
    
    public static Muveletek i() {
        if (instance == null) {
            instance = new Muveletek();
        }
        return instance;
    }

    public KetOp ketOp(String muvelet) {
        return ketOps.get(muvelet);
    }

    public EgyOp egyOp(String muvelet) {
        return egyOps.get(muvelet);
    }
}
