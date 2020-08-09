<?php
include("connect.php");
$page = $_GET['page'];
$idloaisanpham = $_POST["idloaisanpham"];
//$idloaisanpham = 1;
$datasp = 5; //giới hạn số lượng sản phẩm get về
$limit = ($page-1)*$datasp;
$mangsanpham = array();
$sql = "SELECT * FROM sanpham WHERE idloaisanpham = $idloaisanpham LIMIT $limit,$datasp";
$data = mysqli_query($connect, $sql);
while($row = mysqli_fetch_assoc($data))
{
    array_push($mangsanpham, new getSP(
        $row['id'], 
        $row['tensanpham'], 
        $row['giasanpham'], 
        $row['hinhanhsanpham'],
        $row['motasanpham'], 
        $row['idloaisanpham']


    ));
}
echo json_encode($mangsanpham);
class getSP {
    function getSP($idsp, $tensp, $giasp, $imgsp, $motasp, $idloaisp)
    {
        $this->id = $idsp; //id có biến nhận vào là $id
        $this->tensanpham = $tensp;
        $this->giasanpham = $giasp;
        $this->hinhanhsanpham = $imgsp;
        $this->motasanpham = $motasp;
        $this->idloaisanpham = $idloaisp;

    }

}


?>