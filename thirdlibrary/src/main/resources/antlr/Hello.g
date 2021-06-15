grammar Hello; //定义文法的名字

s : 'hello' ID; //匹配关键紫hello和标志符

ID : [a-z]+;
WS : [\t\r\n]+->skip;

