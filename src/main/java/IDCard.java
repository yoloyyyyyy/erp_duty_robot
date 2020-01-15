public class IDCard {
    final int[] vi = {7,10,5,3,6,8,9,2,4,1,10,2,4,5,6,4,3};
    final int[] wi = {1,0,9,8,7,6,5,4,3,2};
    int[] ai = new int[18];

    public IDCard() {
    }

    private String getVerifyCode(String sevenTeenID){
        int remain ;
        if(sevenTeenID.length() == 17){
            int sum = 0;
            for(int i=0;i<17;i++){
                String s = sevenTeenID.substring(i,i+1);
                ai[i] = Integer.parseInt(s);
            }
            for (int j=0;j<17;j++){
                sum += vi[j]*ai[j];
            }
            remain = sum%11;
            return  remain ==2 ? "x":String.valueOf(wi[remain]);
        }else {
            return "出错了";
        }
    }
    
    private String getProvince(){
        int number;
        String str ;
        number = (int)(Math.random()*(64-1)+1);
        if(number<10){
            str ="0"+Integer.toString(number);
            return str;
        }else {
            return Integer.toString(number);
        }
    }

    private String getCity(){
        int number;
        String str ;
        number = (int)(Math.random()*1+1);
        if(number<10){
            str ="0"+Integer.toString(number);
            return str;
        }else {
            return Integer.toString(number);
        }
    }

    private String getXian(){
        int number;
        String str ;
        number = (int)(Math.random()*4+1);
        if(number<10){
            str ="0"+Integer.toString(number);
            return str;
        }else {
            return Integer.toString(number);
        }
    }
    private String getYear(){
        //第 7--10位   年份：1970-2010
        int number;
        number = (int)(Math.random()*(2010-1970)+1970);
        String str;
        if(number<10)
        {
            str ="0"+ Integer.toString(number);
            return str;
        }
        else{
            return Integer.toString(number);
        }
    }

    private String getMonth(){
        //第11 12 位      月份：01-12
        int number ;
        number = (int)(Math.random()*(12-1)+1);
        String str;
        if(number<10)
        {
            str = "0"+Integer.toString(number);
            return str;
        }
        else{
            return Integer.toString(number);
        }
    }

    private String getDay(){
        //第13   14 位    日期：01-28
        int number ;
        number = (int)(Math.random()*(28-1)+1);
        String str;
        if(number<10)
        {
            str = "0"+Integer.toString(number);
            return str;
        }
        else{
            return Integer.toString(number);
        }
    }

    private String getSequence(){
        //第15  16 位        两位：01-99
        int number ;
        number = (int)(Math.random()*(99-1)+1);
        String str;
        if(number<10)
        {
            str = "0"+Integer.toString(number);
            return str;
        }
        else{
            return Integer.toString(number);
        }
    }

    private String getSex(){
        // 第17 位  一位性别：0-9
        int number ;
        number = (int)(Math.random()*9+0);
        return Integer.toString(number);
    }
    private  String get17ID(){
        String str;
        str = this.getProvince()+this.getCity()+this.getXian()+ this.getYear()+ this.getMonth()+ this.getDay() + this.getSequence() + this.getSex()  ;
        return str;
    }
    private  String getIDCard(){
        String str = this.get17ID();
        str = str+this.getVerifyCode(str);
        return str;
    }

    public static void main(String[] args) {
        IDCard idCard = new IDCard();
        System.out.println(idCard.getIDCard());
    }
}
