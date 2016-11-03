package orange.com.androidtool.Widget;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Orange on 2016/11/3.
 * 判断是否输入的是Emoji表情符号的EditText
 */

public class ContainsEmojiEditText extends EditText {

    /**
     * 输入表情前光标的位置
     */
    private int cursorPos;
    /**
     * 输入表情之前的文本
     */
    private String beforeInputText;
    /**
     * 是否重置文本
     */
    private boolean resetText;

    private Context mContext;

    public ContainsEmojiEditText(Context context) {
        super(context);
        this.mContext = context;
        initEditText();

    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initEditText();
    }

    /**
     * 检查是否存在emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是emoji表情
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    /**
     * 初始化EditText控件
     */
    private void initEditText() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!resetText) {
                    cursorPos = getSelectionEnd();
                    beforeInputText = s.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    if (count >= 2) {
                        CharSequence input = s.subSequence(cursorPos, cursorPos + count);
                        if (containsEmoji(input.toString())) {
                            resetText = true;
                            Toast.makeText(mContext, "不支持输入表情符号", Toast.LENGTH_SHORT).show();
                            setText(beforeInputText);
                            CharSequence text = getText();
                            if (text instanceof Spannable) {
                                Spannable spannable = (Spannable) text;
                                Selection.setSelection(spannable, text.length());
                            }
                        }
                    }
                } else {
                    resetText = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

