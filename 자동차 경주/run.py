import sys
import copy
import random
from character import player

speedA = random.randint(100,181)
speedB = random.randint(100,181)
speedC = random.randint(100,181)

player1 = player()
player2 = player()
player3 = player()
player4 = player()
player5 = player()

players = [player1,player2,player3,player4,player5]

print("***************게임 접속***************")

for i in range(1,6):
    print("Player" + str(i) + "의 이름을 입력해 주세요 : ")
    players[(i-1)].set_name()

print()
print("***************캐릭터 선택***************\n")

for j in players:
    j.set_speed()
    print(j.name + "의 고유 속도는 시속 " + str(j.speed) + " 입니다.\n")

print()
print("***************게임 시작***************")

for round_name in range(1, 4):

    print("===============Round  " + str(round_name) + "===============\n")
    player.set_track()
    print("[아이템 적용]")
    for j in players:
        j.set_booster()

    print("\n[경기 진행중...]\n")
    print("[라운드 결과]\n")

    for j in players:
        j.round_result()

    print("\n")

print("***************명예의 전당***************")
records = []
min = [sys.maxsize, sys.maxsize, sys.maxsize]
index = []

# list 안에 모든 player 기록 넣고, 복사본 만들기
for j in players:
    records.append(j.runtime_total)
records_original = copy.deepcopy(records)   # <-> shallow copy 일 때 : 두 배열의 주소값이 같아서 하나가 변경되면 다른 하나도 같이 변경된다.



# for 문 사용해서 가장 작은 값, 그 다음으로 작은 값, ... 이렇게 작은 순서대로 값 3개 뽑아내기
for k in range(0, len(min)):
    for l in range(0, len(records)):
        if records[l] < min[k]:
            min[k] = copy.deepcopy(records[l])
    del records[records.index(min[k])]

# player 이름을 알기 위해서, 위에서 찾아낸 작은 값들의 index(몇 번째 값이었는지) 알아내기
for m in range(0, 3):
    num1 = min[m]
    for n in range(0, 5):
        num2 = records_original[n]
        if(min[m]==records_original[n]):
            index.append(n)

for i in range(3):
    print(str((i+1)) + "위 : " + str(players[index[i]].name) + " (총 주행 시간 : " + str(round(min[i], 1)) + "초)")
