<?php
include("connect.php");
$sql = "SELECT * FROM loaisanpham";

//b1. Tạo class loaiSP
$data = mysqli_query($connect, $sql);

class loaiSP 

{
    function loaiSP($id, $tenloaiSP,$hinhanhloaiSP)
    {
        $this->id = $id; //id có biến nhận vào là $id
        $this->tenSp = $tenloaiSP;
        $this->hinhanhSp = $hinhanhloaiSP;
    }
}
//2. tạo mảng
$loaisp_array = array();
//3. thêm phần tử của mảng
while($row = mysqli_fetch_assoc($data))
{   
    array_push($loaisp_array,new loaiSP( $row['id'], $row['tenSp'], $row['hinhanhSp'] ));
//     // echo $row['id'].'<br/>';
//     // echo $row['tenSp'].'<br/>';
//     // echo $row['hinhanhSp'].'<br/>';


}
// array_push($loaisp_array, new loaiSP(1, "LapTop", "Hà Nam"));
// array_push($loaisp_array, new loaiSP(2, "Phone", "Hà Nội"));
// array_push($loaisp_array, new loaiSP(3, "Tablet", "Thanh Hóa"));
//4 chuyển đổi mảng thành json

echo json_encode($loaisp_array);



?>