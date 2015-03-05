(* UBID1 : aravindk
   UBID2 : pn33 *)

datatype term= V of string| A of term*term | L of string*term;

fun show((V x))=x
        | show(L(s,a))="L"^s^"."^show(a)
        | show(A(p,q))="("^show(p)^" "^show(q)^")";


val env=[];

fun check_vars(x, y, nil) =     if x = y then true else false
        | check_vars(x, y, (h1,h2)::t) =  
                if x = h1 andalso y = h2 then true (*check_vars(x, y, t)*)
                else if x = h1 andalso y <> h2  then false
                else if x <> h2 andalso y = h2  then false
                else check_vars(x, y, t); 
        (* found->match,  found->not match  not found->match not found-> not match *)

fun alpha2(L(x1,t1), L(x2,t2), env) = alpha2(t1,t2,(x1,x2)::env)
|       alpha2(A(t1,t2), A(t3,t4), env) = alpha2(t1,t3,env) andalso alpha2(t2,t4,env) 
|       alpha2(V(x),V(y),env)= check_vars(x, y, env)
|       alpha2(_,_,env) = false; 



fun alpha(x, y) = alpha2(x, y, env);