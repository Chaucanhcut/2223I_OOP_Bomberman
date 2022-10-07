# 2223I_OOP_Bomberman

# Thành viên
* Trần Đức Anh: https://github.com/trananh91
* Nguyễn Lê Hải Châu: https://github.com/Chaucanhcut

# Mô tả về các đối tượng trong trò chơi
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (__Bomber__, __Enemy__, __Bomb__) và nhóm đối tượng tĩnh (__Grass__, __Wall__, __Brick__, __Door__, __Item__).

 * _Bomber_ là nhân vật chính của trò chơi. _Bomber_ có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.

 * _Enemy_ là các đối tượng mà _Bomber_ phải tiêu diệt hết để có thể qua level. _Enemy_ có thể di chuyển ngẫu nhiên hoặc tự đuổi theo _Bomber_ tùy theo loại _Enemy_. Các loại _Enemy_ sẽ được mô tả cụ thể ở phần dưới.

 * _Bomb_ là đối tượng mà _Bomber_ sẽ đặt và kích hoạt tại các ô _Grass_. Khi đã được kích hoạt, _Bomber_ và _Enemy_ không thể di chuyển vào vị trí _Bomb_. Tuy nhiên ngay khi _Bomber_ vừa đặt và kích hoạt _Bomb_ tại vị trí của mình, _Bomber_ có một lần được đi từ vị trí đặt _Bomb_ ra vị trí bên cạnh. Sau khi kích hoạt 2s, _Bomb_ sẽ tự nổ, các đối tượng _Flame_ được tạo ra.

 * _Grass_ là đối tượng mà _Bomber_ và _Enemy_ có thể di chuyển xuyên qua, và cho phép đặt _Bomb_ lên vị trí của nó.

 * _Wall_ là đối tượng cố định, không thể phá hủy bằng _Bomb_ cũng như không thể đặt _Bomb_ lên được, _Bomber_ và _Enemy_ không thể di chuyển vào đối tượng này.

 * _Brick_ là đối tượng được đặt lên các ô _Grass_, không cho phép đặt _Bomb_ lên nhưng có thể bị phá hủy bởi _Bomb_ được đặt gần đó. _Bomber_ và _Enemy_ thông thường không thể di chuyển vào vị trí _Brick_ khi nó chưa bị phá hủy.

 * _Portal_ là đối tượng được giấu phía sau một đối tượng _Brick_. Khi _Brick_ đó bị phá hủy, _Portal_ sẽ hiện ra và nếu tất cả _Enemy_ đã bị tiêu diệt thì người chơi có thể qua level khác bằng cách di chuyển vào vị trí của _Portal_.

Các __Item__ cũng được giấu phía sau _Brick_ và chỉ hiện ra khi _Brick_ bị phá hủy. _Bomber_ có thể sử dụng __Item__ bằng cách di chuyển vào vị trí của __Item__. Thông tin về chức năng của các __Item__ được liệt kê như dưới đây:

 * _SpeedItem_ Khi sử dụng __Item__ này, _Bomber_ sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp.
 * _FlameItem_ __Item__ này giúp tăng phạm vi ảnh hưởng của _Bomb_ khi nổ (độ dài các _Flame_ lớn hơn).
 * _BombItem_ Thông thường, nếu không có đối tượng _Bomb_ nào đang trong trạng thái kích hoạt, _Bomber_ sẽ được đặt và kích hoạt duy nhất một đối tượng _Bomb_. __Item__ này giúp tăng số lượng _Bomb_ có thể đặt thêm một.

Có nhiều loại __Enemy__ trong __Bomberman__, tuy nhiên trong phiên bản này chỉ cài đặt hai loại __Enemy__ dưới đây:

 * _Balloom_ là __Enemy__ đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định.
 * _Oneal_ có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm và di chuyển "thông minh" hơn so với _Balloom_ (biết đuổi theo _Bomber_).
 
# Mô tả game play, xử lý va chạm và xử lý bom nổ
 * Trong một màn chơi, _Bomber_ sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả __Enemy__ và tìm ra vị trí _Portal_ để có thể qua màn mới

 * _Bomber_ sẽ bị giết khi va chạm với __Enemy__ hoặc thuộc phạm vi _Bomb_ nổ. Lúc đó trò chơi kết thúc.

 * __Enemy__ bị tiêu diệt khi thuộc phạm vi _Bomb_ nổ.

 * Một đối tượng thuộc phạm vi _Bomb_ nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng _Bomb_ nổ.

 * Khi _Bomb_ nổ, một _Flame_ trung tâm tại vị trí _Bomb_ nổ và bốn _Flame_ tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên/dưới/trái/phải. Độ dài bốn _Flame_ xung quanh mặc định là 1 đơn vị, được tăng lên khi _Bomber_ sử dụng các __FlameItem__.

 * Khi các _Flame_ xuất hiện, nếu có một đối tượng thuộc loại _Brick/Wall_ nằm trên vị trí một trong các _Flame_ thì độ dài _Flame_ đó sẽ được giảm đi để sao cho _Flame_ chỉ xuất hiện đến vị trí đối tượng _Brick/Wall_ theo hướng xuất hiện. Lúc đó chỉ có đối tượng _Brick/Wall_ bị ảnh hưởng bởi _Flame_, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản _Flame_ là một đối tượng _Bomb_ khác thì đối tượng _Bomb_ đó cũng sẽ nổ ngay lập tức.
