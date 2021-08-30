import random

class player:

    track_length = 0
    booster = ["booster", "banana_slip"]
    numberA = random.randint(100,181)
    numberB = random.randint(100,181)
    numberC = random.randint(100,181)

    def __init__(self):
        self.name = ""
        self.character = 0
        self.speed = 0
        self.booster_speed = 0
        self.runtime = 0
        self.runtime_total = 0

    def set_name(self):
        self.name = input()

    def set_speed(self):
        character_number = int(input(self.name + "의 캐릭터 선택 차례입니다. 1 2 3 중 하나의 값을 입력해 주세요 : "))
        if character_number == 1:
            self.speed = player.numberA
        elif character_number == 2:
            self.speed = player.numberB
        elif character_number == 3:
            self.speed = player.numberC


    @staticmethod
    def set_track():
        player.track_length = random.randint(5,31)
        print("이번 라운드의 트랙 길이는 " + str(player.track_length) + "km 입니다.")


    def set_booster(self):
        self.booster_speed = 0
        choice = random.choice(player.booster)
        if "booster" == choice:
            self.booster_speed = self.speed + random.randint(30,61)
            print("Player " + self.name + "는 booster 덕분에 시속 " + str(self.booster_speed) + "으로 이번 트랙을 질주합니다! 파이팅~~")
        elif "banana_slip" == choice:
            self.booster_speed = self.speed - random.randint(20,41)
            print("Player " + self.name + "는 banana_slip 때문에 시속 " + str(self.booster_speed) + "으로 이번 트랙을 질주합니다! 파이팅 ㅠㅠ")

    def round_result(self):
        self.runtime = 0
        self.runtime = player.track_length/self.booster_speed*3600
        self.runtime_total += self.runtime
        print("Player " + self.name + "의 주행 시간 : " + str(self.runtime))




