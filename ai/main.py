from flask import Flask, escape, request, jsonify
import pandas as pd
import re, kss

from konlpy.tag import Okt
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.models import load_model

# http 통신하는 import

app = Flask(__name__)
app.debug = True

@app.route('/')

def hello():
    # name = request.args.get("name", "World")
    return f'Hello, {escape("aeuaycieu")}'

@app.route('/predict', methods=['POST'])
def make_prediction():
    if request.method == 'POST':
        
        # 사연(story) data 받아오기
        params = request.get_json()
        
        # 로컬에 사연 data를 저장
        file = open('./data.txt', 'w')
        text_data = params['content']
        file.write(text_data)
        file.close()
        
        # # 문장 전처리 수행
        okt = Okt()
        stop_word = pd.read_csv('./ko_stopword_2.csv', encoding='CP949', sep='nan')
        stop_word = pd.DataFrame(stop_word)
        stop_words = stop_word.to_numpy()
        
        clean_sent = []
        clean_text=''
        for sent in kss.split_sentences(text_data):
            clean_text += sent_preprocess(sent, okt, stop_words) + ' '
        clean_text = clean_text[:-1]
        clean_sent.append(clean_text)
        
        tokenizer = Tokenizer()

        # # 문장 내 키워드(명사) 추출 및 빈도수 순으로 출력
        nouns_sent = okt.nouns(clean_sent[0])
        tokenizer.fit_on_texts(nouns_sent)
        word_index = tokenizer.word_index
        keyword = []
        for key in word_index:
            keyword.append(key)
            
        tokenizer.fit_on_texts(clean_sent)
        encoded = tokenizer.texts_to_sequences(clean_sent)
        text_pad = pad_sequences(encoded, maxlen=300, padding='post')
        
        score = model.predict(text_pad)
        
        if(score[0][0]<0.4):
            score = 0
        elif((score[0][0]>=0.4) & (score[0][0]<0.6)):
            score = 1
        else:
            score = 2
        
        return jsonify({'result' : 0, 'keyword' : keyword[:3]})

def sent_preprocess(text, okt, stop_words):
    retext_1 = re.sub("[a-zA-Zㄱ-ㅎㅏ-ㅣ,\\n\!?@#$%^&*()~`]", "", text)
    retext_2 = okt.morphs(retext_1, stem=False)
    
    clean_text = [token for token in retext_2 if not token in stop_words]
    
    clean_text = ' '.join(clean_text)
    return clean_text

if __name__=="__main__":
    # model = joblib.load('./model/model.pkl')
    model = load_model('./ml_load/lstm_2.h5')
    
    app.run()
