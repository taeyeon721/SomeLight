# AI - Deep Learning

딥러닝은 입력층과 출력층 사이에 여러 개의 은닉층으로 이루어진 신경망이다.

층이 깊어질수록 모듈과 함수에 따른 하이퍼파라미터(hyper-parameter)도 비례하여 많아지기에,

이 하이퍼파라미터를 결정하여 모델이 정확한 결과를 도출할 수 있도록 하는 것이 학습의 핵심이다.

<aside>
💡 **학습** : 기계가 실제값과 예측값의 오차로부터 optimizer를 통해서 가중치를 업데이트하는 과정

</aside>

그러기 위해서는 손실 함수(Loss Function)을 정의해야 하며 이를 최적화(Optimization)해야한다.

인공 신경망의 학습은 오차를 최소화하는 가중치를 찾는 목적으로 순전파와 역전파를 반복하는 것을 말한다.

## Tokenizer

## Loss Function

- 실제값과 예측값의 차이를 수치화해주는 함수
- 두 값의 차이(=오차)가 클 수록 손실 함수의 값은 크고, 오차가 작을 수록 손실 함수의 값은 작아진다.
- 손실 함수의 값을 최소화하는 두 개의 매개변수인 가중치 w와 편향 b의 값을 찾는 것이 딥러닝의 학습 과정

**Categorical Cross-Entropy**

- 사용할 손실 함수
- 출력층에서 softmax 함수를 사용하는 다중 클래스 분류(Multi-Class Classification)일 경우 사용
- 만약 레이블에 대해서 one-hot encoding을 생략하고, 정수값을 가진 레이블에 대해서 다중 클래스 분류를 수행하고 싶으면 ‘sparse_categorical_crossentropy’ 사용

```python
# categorical cross-entropy
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])
# tensorflow
model.compile(lose=tf.keras.losses.CategoricalCrossentropy(), optimizer='adam', metrics=['acc'])

# sparse categorical cross-entropy
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])
# tensorflow
model.compile(loss=tf.keras.losses.SparseCategoricalCrossentropy(), optimizer='adam', metrics=['acc'])
```

## Gradient Descent

- MLDL 알고리즘을 학습시킬 때 사용하는 방법 중 하나로 1차 근삿값 발견용 최적화 알고리즘
- 함수의 기울기(경사)를 구하여 기울기가 낮은 쪽으로 계속 이동시켜 극값(최적값)에 이를 때까지 반복
- 손실 함수의 값을 줄여나가면서 학습하는 방법 중 배치(Batch)는 가중치 등의 매개 변수의 값을 조정하기 위해 사용하는 데이터의 양

**Mini-Batch Gradient Descent**

- 사용할 경사 하강법
- 배치 크기를 지정하여 해당 데이터 개수만큼에 대하여 계산하여 매개 변수의 값을 조정하는 경사 하강법
- 배치 크기는 일반적으로 2의 n제곱에 해당하는 숫자로 선택하는 것이 보편적
- batch_size의 default는 32

```python
model.fit(X_train, y_train, batch_size=128)
```

## Optimizer

- 최적화(Optimization)는 손실 함수의 결과값을 최소화하는 모델의 파라미터(가중치)를 찾는 것을 의미하며, Optimizer는 최적화의 알고리즘

**Adam**

- 사용할 최적화 알고리즘
- RMSprop + Momentum, 방향과 학습률 두 가지를 모두 잡는 방법

```python
# direct setting
adam = tf.keras.optimizers.Adam(lr=0.001, beta_1=0.9, beta_2=0.999, epsilon=None, decay=0.0, amsgrad=False)
model.compile(loss='categorical_crossentropy', optimizer=adam, metrics=['acc'])

# default adam
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])
```

## Overfitting

- 학습 데이터에 모델이 과적합되는 현상은 모델의 성능을 떨어트리는 주요 이슈
- 과적합을 막는 방법
    1. 데이터의 양 늘리기
    2. 모델의 복잡도 줄이기
    3. 가중치 규제(Regularization) 적용
        1. L1, L2
    4. **Dropout**
        1. 신경망 학습 시에만 사용하고, 예측 시에는 사용하지 않는 것이 일반적
        
        ```python
        from tensorflow.keras.models import Sequential
        from tensorflow.keras.layers import Dropout, Dense
        
        max_words = 10000
        num_classes = 46
        
        model = Sequential()
        model.add(Dense(256, input_shape=(max_words,), activation='relu'))
        model.add(Dropout(0.5))
        model.add(Dense(128, activation='relu'))
        model.add(Dropout(0.5))
        model.add(Dense(num_classes, activation='softmax'))
        
        ```
        

## Weight Initialization

- Gradient Vanishing/Exploding 막는 방법
- 같은 모델을 훈련시키더라도 가중치가 초기에 어떤 값을 가졌느냐에 따라서 모델의 훈련 결과가 달라지기도 한다.
    - 가중치 초기화만 적절히 해줘도 기울기 소실 문제와 같은 문제가 완화됨
    1. Xavier Initialization
    2. He Initialization
- ReLU + HE Initialization 방법이 효율적

### 예제(MLP)

****20개 뉴스 그룹(Twenty Newsgroups) 데이터에 대한 이해****

[https://wikidocs.net/49071](https://wikidocs.net/49071)

```python
import pandas as pd
from sklearn.datasets import fetch_20newsgroups
import matplotlib.pyplot as plt
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.utils import to_categorical

newsdata = fetch_20newsgroups(subset='train')
data = pd.DataFrame(newsdata.data, columns=['email'])
data['target']=pd.Series(newsdata.target)
data.info()

newsdata_test = fetch_20newsgroups(subset='test', shuffle=True)
train_email = data['email']
train_label=data['target']
test_email=newsdata_test.data
test_label=newsdata_test.target

vocab_size=10000
num_classes=20

def prepare_data(train_data, test_data, mode):
  tokenizer=Tokenizer(num_words=vocab_size)
  tokenizer.fit_on_texts(train_data)
  X_train=tokenizer.texts_to_matrix(train_data, mode=mode)
  X_test = tokenizer.texts_to_matrix(test_data, mode=mode)
  return X_train, X_test, tokenizer.index_word

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout

def fit_and_evaluate(X_train, y_train, X_test, y_test):
  model = Sequential()
  model.add(Dense(256, input_shape=(vocab_size,), activation='relu'))
  model.add(Dropout(0.5))
  model.add(Dense(128, activation='relu'))
  model.add(Dropout(0.5))
  model.add(Dense(num_classes, activation='softmax'))

  model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
  model.fit(X_train, y_train, batch_size=128, epochs=5, verbose=1, validation_split=0.1)
  score = model.evaluate(X_test, y_test, batch_size=128, verbose=0)
  return score[1]

modes = ['binary', 'count', 'tfidf', 'freq']

for mode in modes:
  X_train, X_test, _ = prepare_data(train_email, test_email, mode)
  score = fit_and_evaluate(X_train, y_train, X_test, y_test)
  print(mode+' 모드의 테스트 정확도: ', score)
```

| Epoch 1/5
80/80 [==============================] - 4s 40ms/step - loss: 2.2590 - accuracy: 0.3417 - val_loss: 0.9385 - val_accuracy: 0.8198
Epoch 2/5
80/80 [==============================] - 4s 45ms/step - loss: 0.8611 - accuracy: 0.7619 - val_loss: 0.4584 - val_accuracy: 0.8958
…
Epoch 5/5
80/80 [==============================] - 3s 36ms/step - loss: 0.1664 - accuracy: 0.9584 - val_loss: 0.2924 - val_accuracy: 0.9161
binary 모드의 테스트 정확도:  0.8291290402412415
Epoch 1/5
80/80 [==============================] - 5s 49ms/step - loss: 2.8471 - accuracy: 0.2392 - val_loss: 1.6509 - val_accuracy: 0.7253
Epoch 2/5
80/80 [==============================] - 3s 36ms/step - loss: 1.4439 - accuracy: 0.6166 - val_loss: 0.7141 - val_accuracy: 0.8551
…
Epoch 5/5
80/80 [==============================] - 3s 44ms/step - loss: 0.3810 - accuracy: 0.9159 - val_loss: 0.4007 - val_accuracy: 0.8993
count 모드의 테스트 정확도:  0.8158523440361023
Epoch 1/5
80/80 [==============================] - 4s 41ms/step - loss: 2.2124 - accuracy: 0.3645 - val_loss: 0.7934 - val_accuracy: 0.8277
Epoch 2/5
80/80 [==============================] - 4s 46ms/step - loss: 0.8355 - accuracy: 0.7733 - val_loss: 0.4380 - val_accuracy: 0.8905
…
Epoch 5/5
80/80 [==============================] - 3s 40ms/step - loss: 0.2247 - accuracy: 0.9482 - val_loss: 0.3411 - val_accuracy: 0.9117
tfidf 모드의 테스트 정확도:  0.8313860893249512
Epoch 1/5
80/80 [==============================] - 5s 50ms/step - loss: 2.9785 - accuracy: 0.0940 - val_loss: 2.9296 - val_accuracy: 0.3401
Epoch 2/5
80/80 [==============================] - 3s 40ms/step - loss: 2.7296 - accuracy: 0.2117 - val_loss: 2.4050 - val_accuracy: 0.3534
…
Epoch 5/5
80/80 [==============================] - 3s 41ms/step - loss: 1.4301 - accuracy: 0.5566 - val_loss: 1.2187 - val_accuracy: 0.6908
freq 모드의 테스트 정확도:  0.6566649079322815  |
| --- |

[딥 러닝을 이용한 자연어 처리 입문](https://wikidocs.net/book/2155)