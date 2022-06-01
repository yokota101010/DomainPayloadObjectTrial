# DomainPayloadObjectTrial

## コンセプト

UI層とドメイン層の情報受け渡しをDomainPayloadObject（DPO）[^1]方式で行うことを目的に開発したフレームワーク。

### DPO方式のメリット

- バックエンド側
  - DTO方式と比較して値の詰め替えが不要となるので、設計がシンプルになりメモリ消費が小さい。
- フロントエンド側
  - DTO方式の場合、UI層からドメイン層への情報受け渡しには画面入力を行わないhidden属性のinput項目を考慮しなければいけないが、エンティティの参照を直接連携するDPO方式では考慮不要なので構成がシンプルになる。

### DPO方式のデメリット

- UI層にドメインモデルを公開することになるので、UI層とドメイン層が蜜結合になる可能性がある（例えば、ドメインモデルが変更になったときUIも影響を受けやすくなる）。

[^1]:UIに必要な集約への参照を保持する入れ物（詳しくは参考文献#2#3参照）。

## 制約事項

- ベースとなるフレームワークはSpring＋Thymeleaf。
- DPOに保持する集約の参照は、対象のユースケースで中心となる1個のみとする（1個の集約のコレクションはOK）。つまり、1ユースケースで更新を行える集約は1個。
  - DPOに複数の集約を保持させた場合、集約間の結果整合性をフレームワークが担保する必要があるが、この役割をフレームワークで担うのは困難な為。
- 中心となる集約と識別子で関連付く集約がUIのレンダリングに必要な場合、リポジトリを参照専用のコレクションと考えて、直接UI層で使用する。
  - フレームワークが作成を強制するリポジトリサービス（リポジトリの参照を保持する入れ物）をThymeleafから使用可能とする。

## 使用方法

### バックエンド側

- エンティティの定義方法

エンティティのフィールドに直接バリデーション用のアノテーションを付与する。ネスト構造がある場合は@validを付ける。また、NullPointerExceptionの発生を回避する為、初期値の定義も行う。

```java
@Data
public class Division {

	@Valid
	@NotNull
	private OrganizationId organizationId = new OrganizationId("");

	@Valid
	@NotNull
	private DivisionId divisionId = new DivisionId("");

	@NotBlank
	private String name = "";

	@NotNull
	private String description = "";

	private int numOfEmployees = 0;

	//コンストラクタ等の記載は省略
}
```

- DPOの作成とエンティティ登録方法
  - 独自アノテーション`@DomainPayloadObject`を付与する。
  - ユースケースで操作対象となるエンティティの参照を1個のみ登録する（エンティティのListやMapも可）。
  - コントローラの引数とする（これによりMODELに自動登録される）。

例えば、部署情報一覧を扱うユースケースでは下記のようにDPOを作成する。エンティティの登録はコントローラの中で行う。

```java
@Data
@DomainPayloadObject
public class DivisionListDpo {
	private List<Division> divisionList;
}
```

- リポジトリサービスの作成とリポジトリ登録方法

フレームワークで用意したRepositoryServiceインタフェースを実装する。例えば下記のように作成する。

```java
@Service
public class RepositoryServiceImpl implements RepositoryService {

	@Autowired
	private DivisionRepository divisionRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public DivisionRepository getDivisionRepository() {
		return divisionRepository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
}
```

### フロントエンド側

- 個別画面のHTML作成方法

- Thymeleafからのリポジトリ使用方法

### その他の注意事項

- 識別子を値オブジェクトで作成する場合

## 設計思想

画面入力を行わないエンティティのフィールドを自動的にhidden属性のinput項目としてformタグに仕込むことによって、画面入力した項目だけが更新されたエンティティがUI層からドメイン層へ戻ってくるように振る舞う。

### バックエンド側

- FieldsToMap

DPOに登録したエンティティのフィールドをMap形式で出力する（キーはフィールド名、バリューは値）。フィールド名はThymeleafでinput項目のname属性に仕込める形式に加工する。オブジェクトのネスト構造にも対応するので、ルートエンティティから集約内のフィールドを辿ることが出来る。出力結果はMODEL経由でThymeleafに引き渡し（"fieldsToMapItems"という名前で連携）、フロントエンド側のフレームワークで使用する。

- FieldsToMapAspect

`@Contoroller`が付与されたクラスに対してAOP（AfterReturning）でFieldsToMapを呼び出す（利用者はFieldsToMapの呼び出しを意識する必要が無い）。FieldsToMapに渡すDPOは、コントローラメソッドの引数の中から`@DomainPayloadObject`が付与されたものを選別する。

### フロントエンド側

- fieldsToMapBase.html

外側の枠組みをフレームワークとして用意し、内部に個別画面のHTMLを組み込むことができる形式とする。枠組みにはjavascriptを記載する。javascriptでは、FieldsToMapで作成したMapの情報展開を行った上で個別画面にあるformタグのinput項目を検索し、エンティティのフィールドだがinput項目に含まれない項目をhidden属性のinput項目としてformタグに追加する。

![図layout](https://user-images.githubusercontent.com/49588152/171330705-1d3c40f0-c565-4f21-996e-73825b65de3f.jpg)

## 適用例

お仕事マップ

https://github.com/yokota101010/WorkFlowMap

※Eclipse上でDomainPayloadObjectTrialを依存先プロジェクトとして設定することで動作。

## 参考文献
1. 後悔しないためのSpring Boot 入門書：Spring 解体新書（第2版）（田村達也 (著) ）
1. 実践ドメイン駆動設計（ヴォーン・ヴァーノン (著), 高木 正弘 (翻訳) ）
1. 「実践ドメイン駆動設計」から学ぶDDDの実装入門（WINGSプロジェクト 青木淳夫 (著), 山田 祥寛 (監修)）
