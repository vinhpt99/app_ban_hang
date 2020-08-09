<?php 
include("connect.php");
$sql = "SELECT * FROM sanpham ORDER BY id DESC LIMIT 6";
//ORDER BY id DESC LIMIT 6

//b1. Tạo class loaiSP
$data = mysqli_query($connect, $sql);

class loaiSP 

{
    function loaiSP($idsp, $tensp, $giasp, $imgsp, $motasp, $idloaisp)
    {
        $this->id = $idsp; //id có biến nhận vào là $id
        $this->tensanpham = $tensp;
        $this->giasanpham = $giasp;
        $this->hinhanhsanpham = $imgsp;
        $this->motasanpham = $motasp;
        $this->idloaisanpham = $idloaisp;
    }
}
//2. tạo mảng
$loaisp_array = array();
//3. thêm phần tử của mảng
while($row = mysqli_fetch_assoc($data))
{   
    array_push($loaisp_array,new loaiSP( $row['id'], $row['tensanpham'], $row['giasanpham'], $row['hinhanhsanpham'],
    $row['motasanpham']  , $row['idloaisanpham']
));


}

//4 chuyển đổi mảng thành json

echo json_encode($loaisp_array);





?>