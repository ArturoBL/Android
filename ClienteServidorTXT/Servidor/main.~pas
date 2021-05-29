unit main;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ScktComp;

type
  TForm1 = class(TForm)
    ss: TServerSocket;
    mmo: TMemo;
    procedure ssListen(Sender: TObject; Socket: TCustomWinSocket);
    procedure ssAccept(Sender: TObject; Socket: TCustomWinSocket);
    procedure ssClientRead(Sender: TObject; Socket: TCustomWinSocket);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

procedure TForm1.ssListen(Sender: TObject; Socket: TCustomWinSocket);
begin
  mmo.Lines.Add('Listening port: ' + inttostr(ss.Port));
end;

procedure TForm1.ssAccept(Sender: TObject; Socket: TCustomWinSocket);
begin
  mmo.Lines.Add('');
  mmo.Lines.Add('--------------------------------');
  mmo.Lines.Add('Client connected...');
  mmo.Lines.Add('Host: ' + socket.RemoteHost);
  mmo.Lines.Add('Address: ' + socket.RemoteAddress);
end;

procedure TForm1.ssClientRead(Sender: TObject; Socket: TCustomWinSocket);
var
  utf8: UTF8String;
  latin1: AnsiString;
  ws: WideString;
  len: Integer;
begin
  utf8 := socket.ReceiveText; // your source UTF-8 string
  len := MultiByteToWideChar(CP_UTF8, 0, PAnsiChar(utf8), Length(utf8), nil, 0);
  SetLength(ws, len);
  MultiByteToWideChar(CP_UTF8, 0, PAnsiChar(utf8), Length(utf8), PWideChar(ws), len);
  len := WideCharToMultiByte(28591, 0, PWideChar(ws), Length(ws), nil, 0, nil, nil);
  SetLength(latin1, len);
  WideCharToMultiByte(28591, 0, PWideChar(ws), Length(ws), PAnsiChar(latin1), len, nil, nil);
  mmo.Lines.Add('Message: ' + trim(ws));
end;

end.
