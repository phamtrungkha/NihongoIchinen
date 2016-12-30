package com.ptk.nihongoichinen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private static final String INFO = "Chào mừng bạn đến với ứng dụng Nihongo Ichinen. <br/>Mục đích của ứng dụng này hỗ trợ cho những bạn có định hướng học tiếng Nhật trong vong 1 năm có thể đến trình độ N2 cũng như khả năng giao tiếp lưu loát.<br/>Định hướng dài lâu của ứng dụng nhằm hỗ trợ mọi kỹ năng cho bạn như Nghe, đọc, viết, kanji, đàm thoại. Hiện tại ứng dụng chỉ cung cấp chức năng đọc, viết Kanji (nhìn hiragana viết ra giấy từ hán tự), chức năng phản xạ (nghe và trả lời câu hỏi). Ở những phiên bản kế tiếp chúng tôi sẽ cố gắng bổ sung các bài học cũng như những chức năng như đã kể trên.<br/>Vì vẫn còn đang trong thời gian phát triển nên sẽ còn rất nhiều thiếu sót mong bạn thông cảm và mong sẽ nhận được những góp ý quý báo từ bạn.<br/>Xin chân thành cảm ơn!<br/>" +
            "Kính ghi: Phạm Trung Kha. Email: phamtrungkha@gmail.com";
    private static final String CACHHOC = "Sau đây là cách sử dụng từ thành phần của ứng dụng:<br/><br/>" +
            "1. Kanji:<br/>" +
            "&nbsp&nbspSau khi chọn bài học ứng dụng sẽ hiển thị ra một danh sách những từ cần học. Bạn nên học thuộc hoặc ít nhất sơ qua 1 lần. Nếu không tick vào Hira2Kanji,  sau khi ấn vào nút Bắt đầu học ứng dụng sẽ hiển thị từng từ hiragana, bạn hãy cố gắng nhớ và viết chúng ra giấy. Khi cảm thấy mình đã thuộc từ nào đó hãy ấn Lờ đi, chưa thuộc thì ấn Tiếp theo. Ở trang trước khi bạn không tick vào Hira2Kanji ứng dụng sẽ cho bạn học theo chiều ngược lại nghĩa là nhìn chữ Kanji đọc lên bằng Hiragana tương ứng<br/><br/>" +
            "2. IT Kotoba<br/>" +
            "&nbsp&nbspĐây là phần học chuyên cho ngành IT, cách dùng cũng tương đối dễ hiểu vì tương tự như chức năng Kanji, bạn  chỉ cần lưu ý ở màn hình danh sách từ cần học thay vì chọn Hira sang Kanji hay ngược lại thì bạn có thêm nhiều lựa chọn hơn, cụ thể bạn có thể chọn 1 trong 4 loại sau xuất hiện để bạn tìm nghĩa tương ứng: Kanji, Hiragana, Katakana, Ngữ nghĩa.<br/><br/>" +
            "3. Kaitou<br/>" +
            "&nbsp&nbspĐây là phần học giúp bạn nâng cao khả năng giao tiếp. Khuyến nghị mà chúng tôi đưa ra để có thể mang lại hiệu quả cao nhất như sau: bạn hãy dùng chức năng này suốt ngày, ứng dụng sẽ random ra những câu hỏi và bạn sẽ trả lời, giống như đang có một người Nhật đang nói chuyện với bạn vậy. Chú ý trong mục này có yêu cầu nhập khoảng cách, đó là khoảng cách về thời gian cách nhau giữa 2 câu hỏi, nêu bạn có nhiều thời gian bạn có thể chọn giãn cách ngắn, còn nếu muốn đều đặn suốt ngày thì hãy thiết lập thời gian dài hơn (khoảng 2-5 phút). Khi nghe chưa kịp câu hỏi bạn hãy ấn nút NGHE LẠI, nếu lỡ trôi qua 1 câu bạn có thể ấn nút CÂU TRƯỚC, còn nếu trôi qua hai câu thì thôi bạn cứ học tiệp, hết 1 chu kì câu đó sẽ được lặp lại ở chu kì mới. Lúc đang học bạn có thể thoát ứng dụng mà vẫn nghe được bài học. Còn khi muốn kết thúc không học nữa bạn hãy ấn nút TẮT HẲN ở màn hình có nút NGHE LẠI, CÂU TRƯỚC lúc nảy.<br/>" +
            "&nbsp&nbspỨng dụng được viết với mục tiêu ưu tiên chức năng, và những chức năng quan trọng nên vẫn còn nhiều chức năng chưa hoàn chỉnh cũng như giao diện thiết kế không đẹp mắt (nếu ko muốn nói là xấu (-_-)). Mong bạn thông cảm, chúng tôi sẽ khắc phục ở những lần cập nhật sau.<br/>" +
            "&nbsp&nbspMột lần nữa xin chân thành cảm ơn bạn đã sử dụng ứng dụng này! Chúc bạn thành công!";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView = (WebView) findViewById(R.id.about_wv_text);
        //textView.setMovementMethod(new ScrollingMovementMethod());

        Button btnInfo = (Button) findViewById(R.id.about_btn_info);
        btnInfo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                webView.loadDataWithBaseURL(null,"<p style=\"text-align: justify\">" + INFO  + "</p", "text/html", "UTF-8", null);
            }
        });

        Button btnCachHoc = (Button) findViewById(R.id.about_btn_cachhoc);
        btnCachHoc.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                webView.loadDataWithBaseURL(null, "<p style=\"text-align: justify\">" + CACHHOC  + "</p", "text/html", "UTF-8", null);
            }
        });
    }
}
