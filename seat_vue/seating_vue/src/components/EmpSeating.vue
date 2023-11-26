<template>
  <div class="form">
    <h2>員工座位安排系統</h2>
    <label>請選擇員工編號:
      <select v-model="selectedEmployee">
        <option v-for="employee in employees" :value="employee.empId" :key="employee.empId">{{ employee.empId }}</option>
      </select>
    </label>
    <div class="container">
      <div v-for="seat in seats" :key="seat.floorSeatSeq"
        :class="['seat-box', { 'selected': seat.seatStatus === 'selected', 'choosing': seat.seatStatus === 'choosing' }]"
        @click="selectSeat(seat.floorSeatSeq)">

        <div>{{ seat.floorNo }}樓：座位{{ seat.seatNo }}</div>
        <!-- <div v-if="seat.empId">{{ '[員編: ' + seat.empId + ']' }}</div> -->
      </div>
    </div>
    <table>
      <tr>
        <td>
          <div class="availableSeat"></div>
        </td>
        <td>空位</td>
      </tr>
      <tr>
        <td>
          <div class="selectedSeat"></div>
        </td>
        <td>已佔用</td>
      </tr>
      <tr>
        <td>
          <div class="choosingSeat"></div>
        </td>
        <td>請選擇</td>
      </tr>
    </table>
    <div class="buttons">
      <button @click="submit">送出</button>
      <button @click="clean">清除</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const seats = ref([]);
const employees = ref([]);
const selectedEmployee = ref('')
const chooseSeatSeq = ref(null);

onMounted(async () => {
  await loadSeats();
  await loadEmployee();
});

// 所有座位
const loadSeats = async () => {
  const seat_url = `http://localhost:8080/ESun/seat/showAllSeats`;
  try {
    const response = await axios.get(seat_url);
    seats.value = response.data;
  } catch (error) {
    console.error('錯誤', error);
  }
};

// 所有員工
const loadEmployee = async () => {
  const emp_url = `http://localhost:8080/ESun/employee/allEmp`;
  try {
    const response = await axios.get(emp_url);
    employees.value = response.data;
  } catch (error) {
    console.error('錯誤', error);
  }
};

// 選擇座位
const selectSeat = (floorSeatSeq) => {

  seats.value.forEach(s => {
    if (s.seatStatus === 'choosing') {
      s.seatStatus = 'available';
    }
  });

  const seat = seats.value.find(s => s.floorSeatSeq === floorSeatSeq);
  if (seat && seat.seatStatus === 'available') {
    seat.seatStatus = 'choosing';
    chooseSeatSeq.value = floorSeatSeq;
  }
};

// 選擇座位送出
const submit = async () => {
  if (chooseSeatSeq.value !== null && selectedEmployee.value) {
    const seat = seats.value.find(s => s.floorSeatSeq === chooseSeatSeq.value);
    if (seat && seat.seatStatus === 'choosing') {
      try {
        const response = await axios.post('http://localhost:8080/ESun/employee/choose', {
          empId: selectedEmployee.value,
          floorSeatSeq: chooseSeatSeq.value
        });

        if (response.status === 200) {
          seat.seatStatus = 'selected';

        }
      } catch (error) {
        console.error('選擇出錯', error);
      }
    }
  }
};



</script>

<style scoped>
.form {
  margin: 10px 250px;
}

.container {
  display: flex;
  flex-wrap: wrap;
  /* 允許項目換行 */
  gap: 10px;
  /* 座位之間的間隔 */
  justify-content: space-between;
  /* 對齊方式 */
  margin: 20px;
}

.seat-box {
  background-color: gainsboro;
  padding: 10px;
  border-radius: 5px;
  /* 圓角 */
  width: 200px;
  /* 根據需要調整寬度 */
  text-align: center;
  /* 文字置中 */
}

.seat-box.selected {
  background-color: red;
}

.seat-box.choosing {
  background-color: greenyellow;
}

.availableSeat,
.selectedSeat,
.choosingSeat {
  width: 20px;
  height: 20px;
  border-radius: 5px;
  margin-bottom: 5px;
}

.availableSeat {
  background-color: gainsboro;
}

.selectedSeat {
  background-color: red;
}

.choosingSeat {
  background-color: greenyellow;
}

.buttons {
  margin-top: 10px;
}
</style>

