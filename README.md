# DomainPayloadObjectTrial

## コンセプト

UI層とドメイン層の情報受け渡しをDomainPayloadObject（DPO）方式で行うことを目的に開発したフレームワーク。

### DPO方式のメリット

- DTO方式と比較して設計がシンプルになる（コントローラとプレゼンテーションモデル（例えばThymeleaf）の設計）。
- 値の詰め替えに伴うメモリ消費が小さい。

### DPO方式のデメリット

- UI層にドメインモデルを公開することになるので、UI層とドメイン層が蜜結合になる可能性がある（例えば、ドメインモデルが変更になったとき、UIも影響を受けやすくなる）。

## 制約事項

## 使用方法

## 設計思想

## 使用例

お仕事マップ
https://github.com/yokota101010/WorkFlowMap

※Eclipse上でDomainPayloadObjectTrialを依存先プロジェクトとして設定することで動作。
