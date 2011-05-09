package jwguo;

public class Diophantine
{
    private int a;
    private int b;
    private int c;

    public static final void main(String[] args)
    {
        Diophantine solver = new Diophantine(Integer.valueOf(args[0]),
                                             Integer.valueOf(args[1]),
                                             Integer.valueOf(args[2]));
        solver.calc();
    }

    public Diophantine(int a, int b, int c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void calc()
    {
        /* x = e + fk
         * y = g + hk */
        int e, f, g, h;
        int x0 = 1, x1 = 0, x2 = 0;
        int loopCnt;
        int multi;
        int gcd;

        // use Euclidean algorithm to find gcd(a, b)
        // and use 大衍求一術 to solve the problem
        int _a , _b;
        int tmp;
        int quot;

        if (Math.abs(a) > Math.abs(b)) {
            _a = a;
            _b = b;
        } else {
            _a = b;
            _b = a;
        }
        for (loopCnt = 0; _b != 0; loopCnt++) {
            tmp  = _b;
            quot = _a / _b;
            _b   = _a % _b;
            _a   = tmp;

            if (0 == loopCnt)
                x1 = quot;
            else {
                x2 = quot * x1 + x0;
                x0 = x1;
                x1 = x2;
            }
        }
        gcd = _a;
        // examine for existence of solution
        if (0 == c % gcd) // gcd(a, b) | c
            multi =  c / gcd;
        else {
            System.out.println("No integer solutions");
            return;
        }

        // calculate e, f, g, and h
        if (0 == x2)
            // x2 has never been used, then the wanted coefficient is x1
            e = (int)Math.pow(-1, loopCnt - 1) * x1 * multi;
        else
            // x2  has been used, then the wanted coefficient is x0
            e = (int)Math.pow(-1, loopCnt - 1) * x0 * multi;

        f = b / gcd;
        if (0 < a * b) // the same sign
            h = -1 * (a / gcd);
        else // oppisite sign
            h = (f / Math.abs(f)) * (a / gcd);

        g = (c - a * e) / b;

        System.out.println("x = " + e + " + (" + f + "k)");
        System.out.println("y = " + g + " + (" + h + "k)");
    }
}

