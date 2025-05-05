# 🛒 金森家電 卒業制作：ショッピングカートシステム

## 📌 プロジェクト概要

このプロジェクトは、Java Servlet と JSP を使用して構築されたショッピングカートWebアプリケーションです。  
ユーザー登録、ログイン、商品一覧表示、カート追加・削除・数量更新、注文処理、購入履歴の表示など、基本的なECサイトの機能を備えています。  
Java Web開発の初学者～中級者向けの実践的な作品です。

## 🛠 使用技術

- Java Servlet / JSP
- JSTL / EL式
- DAOパターン（JDBC）
- H2データベース（インメモリ型）
- HTML / CSS（簡易UI）
- 開発ツール：Eclipse（ビルドツールなし）

## 🔧 実装機能

- 👤 ユーザー登録・ログイン（register.jsp / login.jsp）
- 📦 商品一覧表示（products.jsp）
- 🛒 カートへの追加・数量更新・削除（cart.jsp）
- ✅ 注文確定処理（checkout.jsp）
- 🕓 購入履歴の表示（orderHistory.jsp）
- 🧩 共通ヘッダー（commonHeader.jsp）

## 📁 ディレクトリ構成

```
ShoppingCartProject/
├── src/
│   └── main/
│       ├── java/
│       │   ├── dao/        ← データアクセス層（ProductDAO、UserDAOなど）
│       │   ├── model/      ← JavaBean（Product、CartItemなど）
│       │   ├── servlet/    ← サーブレットクラス（LoginServlet、CheckoutServletなど）
│       │   └── util/       ← DBユーティリティクラス
│       └── webapp/
│           ├── *.jsp       ← 表示画面
│           └── WEB-INF/web.xml ← ルーティング設定
```

## ▶️ 実行方法

1. Eclipse または STS でプロジェクトをインポート（Dynamic Web Project として）
2. Tomcat（バージョン9推奨）をインストールして設定
3. プロジェクトを「Run on Server」で実行
4. ブラウザで [http://localhost:8080/ShoppingCartProject](http://localhost:8080/ShoppingCartProject) にアクセス

## 🧠 学習ポイント

- Java WebアプリケーションにおけるMVC構成
- サーブレットのライフサイクルとリクエスト/レスポンス処理
- DAOパターンとデータベース接続の分離
- JSPとJSTLによる画面の動的生成
- 基本的なEC業務フローの実装体験

## 📌 作者

- 氏名：武　鵬
- メール：wenye0108@gmail.com
