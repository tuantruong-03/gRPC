package account

import (
	context "context"
	"fmt"
)

type Server struct {
	UnimplementedAccountServiceServer
}

// type accountServiceInterface interface {
// 	Register(context.Context, *RegisterRequest) (*RegisterResponse, error)
// 	Login(context.Context, *LoginRequest) (*LoginResponse, error)
// 	mustEmbedUnimplementedAccountServiceServer()
// }

func (s *Server) Register(ctx context.Context, request *RegisterRequest) (*RegisterResponse, error) {
	fmt.Println("request from spring client: ", request.String())
	return &RegisterResponse{Username: request.Username, Fullname: request.Fullname}, nil
}

func (s *Server) Login(ctx context.Context, request *LoginRequest) (*LoginResponse, error) {
	return &LoginResponse{AccessToken: "access_token"}, nil
}

func (s *Server) mustEmbedUnimplementedAccountServiceServer() {

}
