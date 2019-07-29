/*     */ package me.devleo.czpvpessentials;
/*     */ 
/*     */ public enum DefaultFontInfo
/*     */ {
/*   5 */   A('A', 5),  a('a', 5),  B('B', 5),  b('b', 5),  C('C', 5),  c('c', 5),  D('D', 5),  d('d', 5),  E('E', 5),  e('e', 5),  F('F', 
/*   6 */     5),  f('f', 4),  G('G', 5),  g('g', 5),  H('H', 5),  h('h', 5),  I('I', 3),  i('i', 1),  J('J', 5),  j('j', 5),  K(
/*   7 */     'K', 5),  k('k', 4),  L('L', 5),  l('l', 1),  M('M', 5),  m('m', 5),  N('N', 5),  n('n', 5),  O('O', 5),  o(
/*   8 */     'o', 5),  P('P', 5),  p('p', 5),  Q('Q', 5),  q('q', 5),  R('R', 5),  r('r', 5),  S('S', 5),  s('s', 
/*   9 */     5),  T('T', 5),  t('t', 4),  U('U', 5),  u('u', 5),  V('V', 5),  v('v', 5),  W('W', 
/*  10 */     5),  w('w', 5),  X('X', 5),  x('x', 5),  Y('Y', 5),  y('y', 5),  Z('Z', 5),  z('z', 
/*  11 */     5),  NUM_1('1', 5),  NUM_2('2', 5),  NUM_3('3', 5),  NUM_4('4', 
/*  12 */     5),  NUM_5('5', 5),  NUM_6('6', 5),  NUM_7('7', 5),  NUM_8('8', 
/*  13 */     5),  NUM_9('9', 5),  NUM_0('0', 5),  EXCLAMATION_POINT(
/*  14 */     '!', 1),  AT_SYMBOL('@', 6),  NUM_SIGN('#', 
/*  15 */     5),  DOLLAR_SIGN('$', 5),  PERCENT(
/*  16 */     '%', 5),  UP_ARROW('^', 
/*  17 */     5),  AMPERSAND('&', 
/*  18 */     5),  ASTERISK(
/*  19 */     '*', 
/*  20 */     5),  LEFT_PARENTHESIS(
/*  21 */     '(', 
/*  22 */     4),  RIGHT_PERENTHESIS(
/*  23 */     ')', 
/*  24 */     4),  MINUS(
/*  25 */     '-', 
/*  26 */     5),  UNDERSCORE(
/*  27 */     '_', 
/*  28 */     5),  PLUS_SIGN(
/*  29 */     '+', 
/*  30 */     5),  EQUALS_SIGN(
/*  31 */     '=', 
/*  32 */     5),  LEFT_CURL_BRACE(
/*  33 */     '{', 
/*  34 */     4),  RIGHT_CURL_BRACE(
/*  35 */     '}', 
/*  36 */     4),  LEFT_BRACKET(
/*  37 */     '[', 
/*  38 */     3),  RIGHT_BRACKET(
/*  39 */     ']', 
/*  40 */     3),  COLON(
/*  41 */     ':', 
/*  42 */     1),  SEMI_COLON(
/*  43 */     ';', 
/*  44 */     1),  DOUBLE_QUOTE(
/*  45 */     '"', 
/*  46 */     3),  SINGLE_QUOTE(
/*  47 */     '\'', 
/*  48 */     1),  LEFT_ARROW(
/*  49 */     '<', 
/*  50 */     4),  RIGHT_ARROW(
/*  51 */     '>', 
/*  52 */     4),  QUESTION_MARK(
/*  53 */     '?', 
/*  54 */     5),  SLASH(
/*  55 */     '/', 
/*  56 */     5),  BACK_SLASH(
/*  57 */     '\\', 
/*  58 */     5),  LINE(
/*  59 */     '|', 
/*  60 */     1),  TILDE(
/*  61 */     '~', 
/*  62 */     5),  TICK(
/*  63 */     '`', 
/*  64 */     2),  PERIOD(
/*  65 */     '.', 
/*  66 */     1),  COMMA(
/*  67 */     ',', 
/*  68 */     1),  SPACE(
/*  69 */     ' ', 
/*  70 */     3),  DEFAULT(
/*  71 */     'a', 
/*  72 */     4);
/*     */   
/*     */   private char character;
/*     */   private int length;
/*     */   
/*     */   private DefaultFontInfo(char character, int length) {
/*  78 */     this.character = character;
/*  79 */     this.length = length;
/*     */   }
/*     */   
/*     */   public char getCharacter() {
/*  83 */     return this.character;
/*     */   }
/*     */   
/*     */   public int getLength() {
/*  87 */     return this.length;
/*     */   }
/*     */   
/*     */   public int getBoldLength() {
/*  91 */     if (this == SPACE)
/*  92 */       return getLength();
/*  93 */     return this.length + 1;
/*     */   }
/*     */   
/*     */   public static DefaultFontInfo getDefaultFontInfo(char c) { DefaultFontInfo[] arrayOfDefaultFontInfo;
/*  97 */     int i2 = (arrayOfDefaultFontInfo = values()).length; for (int i1 = 0; i1 < i2; i1++) { DefaultFontInfo dFI = arrayOfDefaultFontInfo[i1];
/*  98 */       if (dFI.getCharacter() == c)
/*  99 */         return dFI;
/*     */     }
/* 101 */     return DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\DefaultFontInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */